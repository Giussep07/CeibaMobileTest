package com.giussepr.ceiba.data.repository

import com.giussepr.ceiba.data.mapper.UserResponseMapper
import com.giussepr.ceiba.data.repository.datasource.local.CeibaLocalDataSource
import com.giussepr.ceiba.data.repository.datasource.remote.CeibaRemoteDataSource
import com.giussepr.ceiba.domain.model.ApiException
import com.giussepr.ceiba.domain.model.DomainException
import com.giussepr.ceiba.domain.model.Result
import com.giussepr.ceiba.domain.model.User
import com.giussepr.ceiba.domain.repository.CeibaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CeibaRepositoryImpl @Inject constructor(
    private val ceibaRemoteDataSource: CeibaRemoteDataSource,
    private val ceibaLocalDataSource: CeibaLocalDataSource,
    private val userResponseMapper: UserResponseMapper
) : CeibaRepository {

    override fun getUsers(): Flow<Result<List<User>>> = flow {
        try {

            // Check if there are users in the local database
            val localUsers = ceibaLocalDataSource.getUsers()

            if (localUsers.isNotEmpty()) {
                val users = localUsers.map { userEntity ->
                    User(
                        userEntity.id,
                        userEntity.name,
                        userEntity.email,
                        userEntity.phone,
                    )
                }
                emit(Result.Success(users))
                return@flow
            }

            val response = ceibaRemoteDataSource.getUsers()

            if (response.isSuccessful) {
                response.body()?.let { usersResponse ->
                    if (usersResponse.isNotEmpty()) {
                        val users = usersResponse.map { userResponseMapper.mapToUserDomain(it) }

                        val userEntities = usersResponse.map { userResponseMapper.mapToUserEntity(it) }
                        ceibaLocalDataSource.insertUsers(userEntities)

                        emit(Result.Success(users))
                    } else {
                        emit(Result.Error(DomainException("No users found")))
                    }
                } ?: emit(Result.Error(DomainException("Error getting users")))
            } else {
                emit(Result.Error(ApiException(response.code(), response.message())))
            }
        } catch (e: Exception) {
            emit(Result.Error(DomainException(e.message ?: "Something went wrong")))
        }
    }
}
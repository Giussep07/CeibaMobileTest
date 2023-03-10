package com.giussepr.ceiba.data.repository

import com.giussepr.ceiba.data.mapper.PublicationMapper
import com.giussepr.ceiba.data.mapper.UserResponseMapper
import com.giussepr.ceiba.data.repository.datasource.local.CeibaLocalDataSource
import com.giussepr.ceiba.data.repository.datasource.remote.CeibaRemoteDataSource
import com.giussepr.ceiba.data.utils.NetworkUtils
import com.giussepr.ceiba.domain.model.ApiException
import com.giussepr.ceiba.domain.model.DomainException
import com.giussepr.ceiba.domain.model.Publication
import com.giussepr.ceiba.domain.model.Result
import com.giussepr.ceiba.domain.model.User
import com.giussepr.ceiba.domain.repository.CeibaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CeibaRepositoryImpl @Inject constructor(
    private val ceibaRemoteDataSource: CeibaRemoteDataSource,
    private val ceibaLocalDataSource: CeibaLocalDataSource,
    private val userResponseMapper: UserResponseMapper,
    private val publicationMapper: PublicationMapper,
    private val networkUtils: NetworkUtils,
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

            if (networkUtils.isInternetAvailable()) {
                val response = ceibaRemoteDataSource.getUsers()

                if (response.isSuccessful) {
                    response.body()?.let { usersResponse ->
                        if (usersResponse.isNotEmpty()) {
                            val users = usersResponse.map { userResponseMapper.mapToUserDomain(it) }

                            val userEntities =
                                usersResponse.map { userResponseMapper.mapToUserEntity(it) }
                            ceibaLocalDataSource.insertUsers(userEntities)

                            emit(Result.Success(users))
                        } else {
                            emit(Result.Error(DomainException("No users found")))
                        }
                    } ?: emit(Result.Error(DomainException("Error getting users")))
                } else {
                    emit(Result.Error(ApiException(response.code(), response.message())))
                }
            } else {
                emit(Result.Error(DomainException("Check your internet connection")))
            }
        } catch (e: Exception) {
            emit(Result.Error(DomainException(e.message ?: "Something went wrong")))
        }
    }

    override fun getUserPublications(userId: Int): Flow<Result<List<Publication>>> = flow {
        try {

            // Check if there are publications in the local database

            val localPublications = ceibaLocalDataSource.getUserPublications(userId)

            if (localPublications.isNotEmpty()) {
                val publications = localPublications.map { publicationMapper.mapToDomainPublication(it) }
                emit(Result.Success(publications))
                return@flow
            }

            if (networkUtils.isInternetAvailable()) {
                val response = ceibaRemoteDataSource.getUserPublications(userId)

                if (response.isSuccessful) {
                    response.body()?.let { publicationsResponse ->
                        if (publicationsResponse.isNotEmpty()) {
                            val publications = publicationsResponse.map { publicationMapper.mapToDomainPublication(it) }
                            val publicationEntities = publicationsResponse.map { publicationMapper.mapToEntityPublication(it) }
                            ceibaLocalDataSource.insertPublications(publicationEntities)

                            emit(Result.Success(publications))
                        } else {
                            emit(Result.Error(DomainException("No publications found")))
                        }
                    } ?: emit(Result.Error(DomainException("Error getting publications")))
                } else {
                    emit(Result.Error(ApiException(response.code(), response.message())))
                }
            } else {
                emit(Result.Error(DomainException("Check your internet connection")))
            }
        } catch (e: Exception) {
            emit(Result.Error(DomainException(e.message ?: "Something went wrong")))
        }
    }
}
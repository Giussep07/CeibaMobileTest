package com.giussepr.ceiba.data.repository.datasource.remote

import com.giussepr.ceiba.data.api.JsonPlaceholderApi
import com.giussepr.ceiba.data.model.PublicationResponseDTO
import com.giussepr.ceiba.data.model.UserResponseDTO
import retrofit2.Response
import javax.inject.Inject

class CeibaRemoteDataSourceImpl @Inject constructor(
    private val jsonPlaceholderApi: JsonPlaceholderApi
) : CeibaRemoteDataSource {

    override suspend fun getUsers(): Response<List<UserResponseDTO>> = jsonPlaceholderApi.getUsers()

    override suspend fun getUserPublications(userId: Int): Response<List<PublicationResponseDTO>> =
        jsonPlaceholderApi.getUserPublications(userId)
}
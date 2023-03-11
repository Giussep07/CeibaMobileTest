package com.giussepr.ceiba.data.repository.datasource.remote

import com.giussepr.ceiba.data.model.UserResponseDTO
import retrofit2.Response

interface CeibaRemoteDataSource {
    suspend fun getUsers(): Response<List<UserResponseDTO>>
}
package com.giussepr.ceiba.data.api

import com.giussepr.ceiba.data.model.UserResponseDTO
import retrofit2.Response
import retrofit2.http.GET

interface JsonPlaceholderApi {

    @GET("users")
    suspend fun getUsers(): Response<List<UserResponseDTO>>
}
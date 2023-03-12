package com.giussepr.ceiba.data.api

import com.giussepr.ceiba.data.model.PublicationResponseDTO
import com.giussepr.ceiba.data.model.UserResponseDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface JsonPlaceholderApi {

    @GET("users")
    suspend fun getUsers(): Response<List<UserResponseDTO>>

    @GET("users/{userId}/posts")
    suspend fun getUserPublications(@Path("userId") userId: Int): Response<List<PublicationResponseDTO>>
}
package com.giussepr.ceiba.domain.repository

import com.giussepr.ceiba.domain.model.Publication
import com.giussepr.ceiba.domain.model.Result
import com.giussepr.ceiba.domain.model.User
import kotlinx.coroutines.flow.Flow

interface CeibaRepository {
    fun getUsers(): Flow<Result<List<User>>>
    fun getUserPublications(userId: Int): Flow<Result<List<Publication>>>
}
package com.giussepr.ceiba.data.repository.datasource.local

import com.giussepr.ceiba.data.database.entity.PublicationEntity
import com.giussepr.ceiba.data.database.entity.UserEntity

interface CeibaLocalDataSource {
    suspend fun insertUsers(users: List<UserEntity>)
    suspend fun getUsers(): List<UserEntity>
    suspend fun getUserPublications(userId: Int): List<PublicationEntity>
    suspend fun insertPublications(publicationEntities: List<PublicationEntity>)
}
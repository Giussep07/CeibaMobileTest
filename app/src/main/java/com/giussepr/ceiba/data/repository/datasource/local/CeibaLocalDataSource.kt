package com.giussepr.ceiba.data.repository.datasource.local

import com.giussepr.ceiba.data.database.entity.UserEntity

interface CeibaLocalDataSource {
    suspend fun insertUsers(users: List<UserEntity>)
}
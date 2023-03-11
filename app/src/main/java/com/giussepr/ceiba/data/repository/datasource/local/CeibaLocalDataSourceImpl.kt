package com.giussepr.ceiba.data.repository.datasource.local

import com.giussepr.ceiba.data.database.dao.UserDao
import com.giussepr.ceiba.data.database.entity.UserEntity

class CeibaLocalDataSourceImpl(private val userDao: UserDao): CeibaLocalDataSource {

    override suspend fun insertUsers(users: List<UserEntity>) {
        userDao.insertUsers(users)
    }

    override suspend fun getUsers(): List<UserEntity> {
        return userDao.getUsers()
    }
}
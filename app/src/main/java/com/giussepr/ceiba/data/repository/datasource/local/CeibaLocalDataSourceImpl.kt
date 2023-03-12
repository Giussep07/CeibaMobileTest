package com.giussepr.ceiba.data.repository.datasource.local

import com.giussepr.ceiba.data.database.dao.PublicationDao
import com.giussepr.ceiba.data.database.dao.UserDao
import com.giussepr.ceiba.data.database.entity.PublicationEntity
import com.giussepr.ceiba.data.database.entity.UserEntity

class CeibaLocalDataSourceImpl(
    private val userDao: UserDao,
    private val publicationDao: PublicationDao
) : CeibaLocalDataSource {

    override suspend fun insertUsers(users: List<UserEntity>) {
        userDao.insertUsers(users)
    }

    override suspend fun getUsers(): List<UserEntity> {
        return userDao.getUsers()
    }

    override suspend fun getUserPublications(userId: Int): List<PublicationEntity> {
        return publicationDao.getPublicationsByUserId(userId)
    }

    override suspend fun insertPublications(publicationEntities: List<PublicationEntity>) {
        publicationDao.insertAll(publicationEntities)
    }
}
package com.giussepr.ceiba.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.giussepr.ceiba.data.database.dao.PublicationDao
import com.giussepr.ceiba.data.database.dao.UserDao
import com.giussepr.ceiba.data.database.entity.PublicationEntity
import com.giussepr.ceiba.data.database.entity.UserEntity

@Database(
    entities = [
        UserEntity::class,
        PublicationEntity::class
    ], version = 1, exportSchema = false
)
abstract class CeibaDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun publicationDao(): PublicationDao

}
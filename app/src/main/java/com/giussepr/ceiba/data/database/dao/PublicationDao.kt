package com.giussepr.ceiba.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.giussepr.ceiba.data.database.entity.PublicationEntity

@Dao
interface PublicationDao {

    @Query("SELECT * FROM publication WHERE user_id = :userId")
    suspend fun getPublicationsByUserId(userId: Int): List<PublicationEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(publicationList: List<PublicationEntity>)
}
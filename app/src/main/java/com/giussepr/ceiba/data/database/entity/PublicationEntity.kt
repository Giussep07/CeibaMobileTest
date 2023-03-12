package com.giussepr.ceiba.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "publication",
    indices = [androidx.room.Index(value = ["publication_id"], unique = true)]
)
data class PublicationEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "publication_id")
    val publicationId: Int,
    val title: String,
    val body: String,
    @ColumnInfo(name = "user_id")
    val userId: Int
)

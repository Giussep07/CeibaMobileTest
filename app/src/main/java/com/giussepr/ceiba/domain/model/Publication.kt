package com.giussepr.ceiba.domain.model

data class Publication(
    val id: Int,
    val title: String,
    val body: String,
    val userId: Int
)

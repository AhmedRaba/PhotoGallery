package com.silverkey.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "photos")
data class PhotoEntity(
    @PrimaryKey val id: Int,
    val originalUrl: String,
    val mediumUrl: String,
    val smallUrl: String
)
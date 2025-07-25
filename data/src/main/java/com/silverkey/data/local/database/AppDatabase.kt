package com.silverkey.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.silverkey.data.local.dao.PhotoDao
import com.silverkey.data.local.entity.PhotoEntity

@Database(
    entities = [PhotoEntity::class],
    version = 1,
)
abstract class AppDatabase:RoomDatabase() {
    abstract fun photoDao(): PhotoDao
}
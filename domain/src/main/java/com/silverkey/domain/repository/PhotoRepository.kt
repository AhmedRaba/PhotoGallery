package com.silverkey.domain.repository

import com.silverkey.domain.model.Photo
import kotlinx.coroutines.flow.Flow

interface PhotoRepository {
    suspend fun fetchPhotosFromApi(): List<Photo>
    suspend fun cachePhotos(photos: List<Photo>)
    fun getCachedPhotos(): Flow<List<Photo>>
}
package com.silverkey.data.repository

import com.silverkey.data.local.dao.PhotoDao
import com.silverkey.data.mapper.toDomain
import com.silverkey.data.mapper.toEntity
import com.silverkey.data.remote.api.PhotoApiService
import com.silverkey.domain.model.Photo
import com.silverkey.domain.repository.PhotoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PhotoRepositoryImpl(
    private val api: PhotoApiService,
    private val dao: PhotoDao,
) : PhotoRepository {
    override suspend fun fetchPhotosFromApi(): List<Photo> {
        return api.getPhotos().photos.map { it.toDomain() }

    }

    override suspend fun cachePhotos(photos: List<Photo>) {
        dao.clearPhotos()
        dao.insertPhotos(photos.map { it.toEntity() })
    }

    override fun getCachedPhotos(): Flow<List<Photo>> {
        return dao.getAllPhotos().map { list ->
            list.map { it.toDomain() }
        }
    }
}
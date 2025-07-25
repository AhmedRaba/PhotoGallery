package com.silverkey.domain.usecase

import com.silverkey.domain.model.Photo
import com.silverkey.domain.repository.PhotoRepository

class CachePhotosUseCase(
    private val repository: PhotoRepository
) {
    suspend operator fun invoke(photos: List<Photo>) {
        repository.cachePhotos(photos)
    }
}
package com.silverkey.domain.usecase

import com.silverkey.domain.model.Photo
import com.silverkey.domain.repository.PhotoRepository
import kotlinx.coroutines.flow.Flow

class GetCachedPhotosUseCase(
    private val repository: PhotoRepository,
) {
    operator fun invoke(): Flow<List<Photo>> {
        return repository.getCachedPhotos()
    }
}
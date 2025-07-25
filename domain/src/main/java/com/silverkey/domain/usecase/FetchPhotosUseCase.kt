package com.silverkey.domain.usecase

import com.silverkey.domain.model.Photo
import com.silverkey.domain.repository.PhotoRepository

class FetchPhotosUseCase(
    private val repository: PhotoRepository
) {
    suspend operator fun invoke(): List<Photo> {
        return repository.fetchPhotosFromApi()
    }
}
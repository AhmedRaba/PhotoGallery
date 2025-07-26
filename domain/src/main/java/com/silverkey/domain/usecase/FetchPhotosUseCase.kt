package com.silverkey.domain.usecase

import com.silverkey.domain.model.Photo
import com.silverkey.domain.repository.PhotoRepository
import com.silverkey.domain.utils.ResultState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class FetchPhotosUseCase(
    private val repository: PhotoRepository,
) {
    operator fun invoke(page: Int, perPage: Int): Flow<ResultState<List<Photo>>> = flow {
        emit(ResultState.Loading)

        try {
            val remote = repository.fetchPhotosFromApi(page, perPage)
            if (page == 1) repository.cachePhotos(remote)
            emit(ResultState.Success(remote))
        } catch (e: Exception) {
            emitAll(
                repository.getCachedPhotos().map { local ->
                    if (local.isNotEmpty()) ResultState.Success(local)
                    else ResultState.Error(e.message ?: "Unknown error")
                }
            )
        }
    }
}

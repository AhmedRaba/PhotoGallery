package com.silverkey.photogallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.silverkey.domain.model.Photo
import com.silverkey.domain.usecase.FetchPhotosUseCase
import com.silverkey.domain.utils.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchPhotosUseCase: FetchPhotosUseCase
) : ViewModel() {

    private val _photos = MutableStateFlow<ResultState<List<Photo>>>(ResultState.Loading)
    val photos: StateFlow<ResultState<List<Photo>>> = _photos

    private val _isPaging = MutableStateFlow(false)
    val isPaging: StateFlow<Boolean> = _isPaging

    private var currentPage = 1
    private val perPage = 20
    private var isLoading = false

    init {
        loadMorePhotos()
    }

    fun loadMorePhotos() {
        if (isLoading) return

        isLoading = true
        _isPaging.value = true

        viewModelScope.launch {
            fetchPhotosUseCase(currentPage, perPage).collectLatest { result ->
                when (result) {
                    is ResultState.Success -> {
                        val updatedPhotos = when (val current = _photos.value) {
                            is ResultState.Success -> current.data + result.data
                            else -> result.data
                        }
                        _photos.value = ResultState.Success(updatedPhotos)
                        currentPage++
                    }

                    is ResultState.Error -> {
                        _photos.value = result
                    }

                    is ResultState.Loading -> {
                    }
                }
                isLoading = false
                _isPaging.value = false
            }
        }
    }
}

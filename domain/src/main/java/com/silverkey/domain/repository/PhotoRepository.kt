package com.silverkey.domain.repository

import com.silverkey.domain.model.Photo

interface PhotoRepository {
    suspend fun getPhotos(): List<Photo>
}
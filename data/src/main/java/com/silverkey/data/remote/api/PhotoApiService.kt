package com.silverkey.data.remote.api

import com.silverkey.data.remote.dto.PhotoResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoApiService {
    @GET("curated")
    suspend fun getPhotos(
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 30,
    ): PhotoResponseDto
}

package com.silverkey.data.remote.dto

import com.google.gson.annotations.SerializedName

data class PhotoResponseDto(
@SerializedName("page") val page: Int,
@SerializedName("per_page") val perPage: Int,
@SerializedName("photos") val photos: List<PhotoDto>
)

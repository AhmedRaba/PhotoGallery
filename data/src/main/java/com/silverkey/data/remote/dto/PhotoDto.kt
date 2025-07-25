package com.silverkey.data.remote.dto

import com.google.gson.annotations.SerializedName

data class PhotoDto(
    @SerializedName("id") val id: Int,
    @SerializedName("src") val src: PhotoSrcDto
)

data class PhotoSrcDto(
    @SerializedName("original") val original: String,
    @SerializedName("medium") val medium: String,
    @SerializedName("small") val small: String
)
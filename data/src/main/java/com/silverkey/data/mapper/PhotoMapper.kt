package com.silverkey.data.mapper

import com.silverkey.data.local.entity.PhotoEntity
import com.silverkey.data.remote.dto.PhotoDto
import com.silverkey.domain.model.Photo

fun PhotoDto.toDomain(): Photo = Photo(
    id = id,
    originalUrl = src.original,
    mediumUrl = src.medium,
    smallUrl = src.small
)

fun PhotoEntity.toDomain(): Photo {
    return Photo(
        id = id,
        originalUrl = originalUrl,
        mediumUrl = mediumUrl,
        smallUrl = smallUrl
    )
}

fun Photo.toEntity(): PhotoEntity {
    return PhotoEntity(
        id = id,
        originalUrl = originalUrl,
        mediumUrl = mediumUrl,
        smallUrl = smallUrl
    )
}




package com.example.library.data.remote.dto

import com.example.library.domain.entities.Photo

data class PhotoDto(
    val albumId: Int,
    val id: Int,
    val thumbnailUrl: String,
    val title: String,
    val url: String
)

fun PhotoDto.toPhoto(): Photo {
    return Photo(albumId = albumId, id = id, thumbnailUrl = thumbnailUrl, title = title, url = url)
}
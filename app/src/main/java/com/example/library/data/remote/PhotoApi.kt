package com.example.library.data.remote

import com.example.library.data.remote.dto.PhotoDto
import retrofit2.http.GET

/*
Photo API retrofit interface
 */
interface PhotoApi {

    @GET("photos")
    suspend fun getPhotos(
    ): List<PhotoDto>

}
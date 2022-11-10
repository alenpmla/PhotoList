package com.example.library.domain.repository

import com.example.library.common.Resource
import com.example.library.domain.entities.Photo
import kotlinx.coroutines.flow.Flow

/*
Photo Repository
 */
interface PhotoRepository {
    suspend fun getPhotos(): Flow<Resource<List<Photo>>>
}
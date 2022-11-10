package com.example.library.data.repository

import com.example.library.common.Resource
import com.example.library.data.remote.PhotoApi
import com.example.library.data.remote.dto.toPhoto
import com.example.library.domain.entities.Photo
import com.example.library.domain.repository.PhotoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

/*
Photo Repository Implementation
 */
class PhotoRepositoryImpl constructor(
    private val api: PhotoApi,
) : PhotoRepository {


    override suspend fun getPhotos(): Flow<Resource<List<Photo>>> {
        return flow {
            emit(value = Resource.Loading(true))

            val remoteListings = try {
                val response = api.getPhotos()
                response
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                emit(Resource.Loading(false))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                emit(Resource.Loading(false))
                null
            }

            remoteListings?.let { listings ->
                emit(Resource.Success(
                    data = listings
                        .map {
                            it.toPhoto()
                        }
                ))
                emit(
                    Resource.Loading(
                        isLoading = false
                    )
                )
                return@flow
            }

        }
    }

}
package com.example.library.data.repository

import com.example.library.common.Resource
import com.example.library.data.remote.PhotoApi
import com.example.library.data.remote.dto.PhotoDto
import com.example.library.domain.entities.Photo
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock
import java.io.IOException

class PhotoRepositoryImplTest {
    private lateinit var mockArticleApi: PhotoApi
    private lateinit var repository: PhotoRepositoryImpl
    private lateinit var successResponsePhotoDto: List<PhotoDto>
    private lateinit var successResponsePhoto: List<Photo>

    @Before
    fun setUp() {
        mockArticleApi = mock()
        repository = PhotoRepositoryImpl(mockArticleApi)
        successResponsePhotoDto = listOf(
            PhotoDto(albumId = 2, thumbnailUrl = "thumpurl", id = 3, title = "title", url = "url")
        )
        successResponsePhoto = listOf(
            Photo(
                albumId = 2, thumbnailUrl = "thumpurl", id = 3, title = "title", url = "url"
            )
        )
    }

    @Test
    fun `is loading is initially true on start getPhotos`() = runBlocking {
        Mockito.`when`(mockArticleApi.getPhotos()).thenReturn(emptyList())
        val firstItem = repository.getPhotos().first()
        assertThat((firstItem as Resource.Loading).isLoading).isEqualTo(true)
    }


    @Test
    fun `is loading is false on completion getPhotos`() = runBlocking {
        Mockito.`when`(mockArticleApi.getPhotos()).thenReturn(emptyList())
        val secondItem = repository
            .getPhotos().drop(2).first()
        assertThat((secondItem as Resource.Loading).isLoading).isEqualTo(false)
    }


    @Test
    fun `resource should be an error type if there is an IOException getPhotos`() =
        runBlocking {
            Mockito.`when`(mockArticleApi.getPhotos()).thenAnswer {
                throw  IOException()
            }
            val secondItem = repository
                .getPhotos().drop(1).first()
            assertThat((secondItem as Resource.Error).message).isEqualTo("Couldn't load data")
        }

    @Test
    fun `resource should be an error type if there is an HttpException getPhotos `() =
        runBlocking {
            Mockito.`when`(mockArticleApi.getPhotos()).thenAnswer {
                throw  IOException()
            }
            val secondItem = repository
                .getPhotos().drop(1).first()
            assertThat((secondItem as Resource.Error).message).isEqualTo("Couldn't load data")
        }

    @Test
    fun `resource should a success type if the response is success getPhotos`() =
        runBlocking {
            Mockito.`when`(mockArticleApi.getPhotos())
                .thenReturn(successResponsePhotoDto)
            val secondItem = repository
                .getPhotos().drop(1).first()
            assertThat((secondItem as Resource.Success).data).isEqualTo(
                successResponsePhoto
            )
        }
}
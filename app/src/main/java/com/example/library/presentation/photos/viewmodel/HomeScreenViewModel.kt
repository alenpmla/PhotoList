package com.example.library.presentation.photos.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.library.common.Resource
import com.example.library.domain.repository.PhotoRepository
import com.example.library.presentation.photos.HomeScreenEvent
import com.example.library.presentation.photos.HomeScreenState
import kotlinx.coroutines.launch

/*
ViewModel for home screen
 */
class HomeScreenViewModel constructor(private val repository: PhotoRepository) :
    ViewModel() {
    var state by mutableStateOf(HomeScreenState())

    init {
        viewModelScope.launch {
            getPhotos()
        }
    }

    /*
    Method to get the photo list from the repository notify the states
     */
    private fun getPhotos() {
        viewModelScope.launch {
            repository
                .getPhotos()
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data?.let { listings ->
                                state = state.copy(
                                    photosList = listings,
                                    isError = false
                                )
                            }
                        }
                        is Resource.Error -> {
                            state = state.copy(
                                isError = true
                            )
                        }
                        is Resource.Loading -> {
                            state = state.copy(isLoading = result.isLoading)
                        }
                    }
                }
        }
    }

    /*
    Method to handle the  events from the UI
     */
    fun onEvent(event: HomeScreenEvent) {
        when (event) {
            is HomeScreenEvent.GetPhotosList -> {
                getPhotos()
            }
        }
    }

}
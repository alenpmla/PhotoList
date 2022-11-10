package com.example.library.presentation.photos

import com.example.library.domain.entities.Photo

/*
States in home screen
 */
data class HomeScreenState(
    val photosList: List<Photo> = emptyList(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val isEmpty: Boolean = false,
)

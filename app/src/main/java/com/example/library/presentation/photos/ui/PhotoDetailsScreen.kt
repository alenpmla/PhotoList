package com.example.library.presentation.photos.ui

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.library.R
import com.example.library.presentation.common.AppBarComponent
import com.example.library.ui.theme.AppBg
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun PhotoDetailsScreen(
    navigator: DestinationsNavigator,
    url: String,
) {
    Scaffold(modifier = Modifier,
        isFloatingActionButtonDocked = true,
        backgroundColor = AppBg,
        topBar = {
            AppBarComponent(
                title = stringResource(id = R.string.text_photo_details), showBack = true,
                navigator = navigator
            )
        }) {

        ZoomableImageComponent(imageUrl = url)
    }


}




package com.example.library.presentation.photos.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.library.R
import com.example.library.domain.entities.Photo
import com.example.library.presentation.common.AppBarComponent
import com.example.library.presentation.common.EmptyScreen
import com.example.library.presentation.common.FailureScreen
import com.example.library.presentation.photos.HomeScreenEvent
import com.example.library.presentation.photos.ui.destinations.PhotoDetailsScreenDestination
import com.example.library.presentation.photos.viewmodel.HomeScreenViewModel
import com.example.library.ui.theme.AppBg
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import org.koin.androidx.compose.getViewModel


/*
Composable for home screen
 */
@RootNavGraph(start = true)
@Destination
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator,
) {
    val homeViewModel = getViewModel<HomeScreenViewModel>()
    val state = homeViewModel.state
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(
        color = AppBg
    )
    val isRefreshing = SwipeRefreshState(isRefreshing = state.isLoading)

    Scaffold(
        modifier = Modifier,
        isFloatingActionButtonDocked = true,
        backgroundColor = AppBg,
        topBar = {
            AppBarComponent(
                title = stringResource(id = R.string.app_name),
                navigator = navigator
            )
        }
    ) {
        SwipeRefresh(
            state = isRefreshing,
            onRefresh = {
                homeViewModel.onEvent(HomeScreenEvent.GetPhotosList)
            },
        ) {
            PhotosListingComponent(
                itemList = state.photosList,
                onItemClicked = {
                    navigator.navigate(
                        PhotoDetailsScreenDestination(
                            url = it.url
                        )
                    )
                }
            )

            if (state.isError && state.photosList.isEmpty()) {
                FailureScreen(onRetryClicked = {
                    homeViewModel.onEvent(
                        HomeScreenEvent.GetPhotosList
                    )
                })
            }
            if (state.isEmpty) {
                EmptyScreen(stringResource(R.string.photo_empty_text))
            }
        }


    }
}


/*
Photo listing view
 */
@Composable
fun PhotosListingComponent(
    itemList: List<Photo>,
    onItemClicked: (Photo) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(top = 4.dp, bottom = 4.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(itemList.size) { i ->
            val photo = itemList[i]
            Box(modifier = Modifier.padding(bottom = 4.dp, top = 4.dp)) {
                PhotoListItem(
                    photo,
                    onItemClicked = onItemClicked
                )
            }
        }

    }
}
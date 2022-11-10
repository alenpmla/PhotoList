package com.example.library.di

import com.example.library.common.Constants
import com.example.library.data.remote.PhotoApi
import com.example.library.data.repository.PhotoRepositoryImpl
import com.example.library.domain.repository.PhotoRepository
import com.example.library.presentation.photos.viewmodel.HomeScreenViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


val appModule = module {
    single {

        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BASIC
                    }).build()
            )
            .build()
            .create(PhotoApi::class.java)

    }
    single<PhotoRepository> {
        PhotoRepositoryImpl(get())
    }
    viewModel {
        HomeScreenViewModel(get())
    }
}
package com.example.anavai.main

import com.example.anavai.api_service.ApiService
import com.example.anavai.repositories.CommentRepository
import com.example.anavai.view_models.MediaInstanceViewModel
import com.example.anavai.view_models.MediaViewModel
import com.example.anavai.repositories.MediaInstanceRepository
import com.example.anavai.repositories.MediaRepository
import com.example.anavai.view_models.CommentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module {

    single { ApiService() }

    factory { MediaRepository(get()) }

    viewModel { MediaViewModel(get()) }

    factory { MediaInstanceRepository(get()) }

    viewModel { MediaInstanceViewModel(get()) }

    factory { CommentRepository(get()) }

    viewModel { CommentViewModel(get()) }

}
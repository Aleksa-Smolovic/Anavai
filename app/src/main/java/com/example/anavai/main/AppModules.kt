package com.example.anavai.main

import com.example.anavai.ApiService.ApiService
import com.example.anavai.viewModels.MediaInstanceViewModel
import com.example.anavai.viewModels.MediaViewModel
import com.example.anavai.repositories.MediaInstanceRepository
import com.example.anavai.repositories.MediaRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModules = module {

    single { ApiService() }

    factory { MediaRepository(get()) }

    viewModel { MediaViewModel(get()) }

    factory { MediaInstanceRepository(get()) }

    viewModel { MediaInstanceViewModel(get()) }

}
package com.example.anavai.main

import com.example.anavai.api_service.ApiService
import com.example.anavai.api_service.AuthInterceptor
import com.example.anavai.api_service.ResponseErrorInterceptor
import com.example.anavai.repositories.*
import com.example.anavai.utils.PreferenceProvider
import com.example.anavai.view_models.*
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val LOCAL_IP = "http://192.168.1.69/"
const val BASE_URL = "${LOCAL_IP}api/"

val appModules = module {

    single { PreferenceProvider(get()) }

    factory { MediaRepository(get()) }

    viewModel { MediaViewModel(get(), get()) }

    factory { MediaInstanceRepository(get()) }

    factory { ReviewRepository(get()) }

    viewModel { ReviewViewModel(get()) }

    factory { UserRepository(get(), get()) }

    viewModel { LoginViewModel(get()) }

    factory { CategoryRepository(get()) }

    viewModel { CategoryViewModel(get()) }

    factory { MovieRepository(get()) }

    viewModel { MovieViewModel(get()) }

    factory { ActorRepository(get()) }

    viewModel { ActorViewModel(get()) }

    viewModel { UserViewModel(get()) }

    factory { BlogRepository(get()) }

    viewModel { BlogViewModel(get()) }

}

val networkModule = module {
    single { AuthInterceptor(get()) }
    single { ResponseErrorInterceptor() }
    single { provideOkHttpClient(get(), get()) }
    single { provideRetrofit(get()) }
    single { provideApiService(get()) }
}

fun provideOkHttpClient(
    authInterceptor: AuthInterceptor,
    responseErrorInterceptor: ResponseErrorInterceptor
): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.apply {
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    }

    return OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .addInterceptor(responseErrorInterceptor)
        .addInterceptor(httpLoggingInterceptor)
        .build()
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
}

fun provideApiService(retrofit: Retrofit): ApiService =
    retrofit.create(ApiService::class.java)


package com.example.anavai.ApiService

import com.example.anavai.models.TestResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Call
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

//const val BASE_TEST_API = "https://rickandmortyapi.com/api/"
const val BASE_TEST_API = "https://jsonplaceholder.typicode.com/"

interface ApiService {

    @GET("todos")
    fun getTest():Deferred<List<TestResponse>>

    companion object{
        operator fun invoke(): ApiService{
            val requestInterceptor = Interceptor{
                val url = it.request()
                    .url()
                    .newBuilder()
//                    .addQueryParameter("api")
                    .build()
                val request = it.request()
                    .newBuilder()
                    .url(url)
                    .build()

                return@Interceptor it.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_TEST_API)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }

}
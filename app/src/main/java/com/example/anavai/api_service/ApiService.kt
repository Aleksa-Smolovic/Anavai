package com.example.anavai.api_service

import com.example.anavai.models.Category
import com.example.anavai.models.TestResponse
import kotlinx.coroutines.Deferred
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

//const val BASE_TEST_API = "https://jsonplaceholder.typicode.com/"

interface ApiService {

    @GET("todos")
    fun getTest(): Deferred<List<TestResponse>>

    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<ResponseBody>

    @GET("categories")
    fun getCategories(): Deferred<List<Category>>

//    //companion object is static method
//    companion object {
//        //operator fun invoke is convenient as it let us call fn like ApiService.login... instead of ApiService.create().login ie
//        //invoke is generic fn, like onCreate
//        operator fun invoke(): ApiService {
////            val requestInterceptor = Interceptor {
////                val url = it.request()
////                    .url()
////                    .newBuilder()
//////                    .addQueryParameter("api")
////                    .build()
////                val request = it.request()
////                    .newBuilder()
////                    .url(url)
////                    .build()
////
////                return@Interceptor it.proceed(request)
////            }
//
//            val okHttpClient = OkHttpClient.Builder()
////                .addInterceptor(requestInterceptor)
//                .build()
//
//            return Retrofit.Builder()
//                .client(okHttpClient)
//                .baseUrl(BASE_URL)
//                .addCallAdapterFactory(CoroutineCallAdapterFactory())
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//                .create(ApiService::class.java)
//        }
//    }

}
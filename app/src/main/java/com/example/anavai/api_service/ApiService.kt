package com.example.anavai.api_service

import com.example.anavai.models.*
import kotlinx.coroutines.Deferred
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<ResponseBody>

    @POST("refresh")
    fun refreshToken(): Call<ResponseBody>

    @GET("categories")
    fun getCategories(): Deferred<List<Category>>

    @GET("category/{categoryId}/movies")
    fun getAllMovies(@Path("categoryId") categoryId: Long): Deferred<List<Movie>>

    @GET("movie/{movieId}")
    fun getMovie(@Path("movieId") movieId: Long): Deferred<Movie>

    @GET("movie/{movieId}/reviews")
    fun getReviews(@Path("movieId") movieId: Long): Deferred<List<Review>>

    @GET("movie/{movieId}/actors")
    fun getActors(@Path("movieId") movieId: Long): Deferred<List<Actor>>

    @GET("user-info")
    fun getUserInfo(): Deferred<User>

    @GET("logout")
    fun logout(): Call<Void>

    @GET("blogs")
    fun getBlogs(): Deferred<List<Blog>>

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
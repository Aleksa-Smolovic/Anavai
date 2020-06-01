package com.example.anavai.api_service

import com.example.anavai.utils.PreferenceProvider
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AuthInterceptor(private val preferences: PreferenceProvider) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        if (preferences.getBearerToken() != null) {
            request = request.newBuilder()
                .addHeader("Authorization", "Bearer " + preferences.getBearerToken()!!)
                .build()
        }

//        val requestBuilder: Request.Builder = request.newBuilder()
//        if (preferences.getBearerToken() != null)
//            requestBuilder.addHeader("Authorization", "Bearer " + preferences.getBearerToken()!!).build()
//
//        val response: Response = chain.proceed(request)
//        System.out.println("Logger:112: " + response.body?.string())

        return chain.proceed(request)
    }

}
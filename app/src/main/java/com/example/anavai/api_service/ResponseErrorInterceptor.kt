package com.example.anavai.api_service

import com.example.anavai.repositories.UserRepository
import com.google.gson.JsonObject
import okhttp3.Interceptor
import okhttp3.Response
import org.json.JSONArray
import org.json.JSONObject
import org.json.JSONTokener

class ResponseErrorInterceptor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val response: Response = chain.proceed(request)

//        when (val responseBody = JSONTokener(response.body!!.string()).nextValue()) {
//            is JSONObject -> { //it is a JsonObject
//                if (responseBody.has("status") && responseBody.get("status")
//                        .toString() == "Token is Expired"
//                ) {
//                    System.out.println("Ovdje je expired token")
//                }
//            }
//            is JSONArray -> { //it is a JsonArray
//                System.out.println("Array")
//            }
//        }
        return response
    }

//    private fun Response.createSignedRequest(): Request? = try {
//        val accessToken = authenticationRepository.fetchFreshAccessToken()
//        request.signWithToken(accessToken)
//    } catch (error: Throwable) {
//        Logger.error(error, "Failed to re-sign request")
//        null
//    }

}
package com.example.anavai.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.anavai.api_service.ApiService
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository(private val apiService: ApiService) {

    fun login(email: String, password: String): LiveData<String> {

        val loginResponse = MutableLiveData<String>()

        apiService.login(email, password)
            .enqueue(object : Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    loginResponse.value = t.message
                }
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    loginResponse.value =
                        if (response.isSuccessful) response.body()
                            ?.string() else response.errorBody()?.string()
                }
            })

        return loginResponse
    }

}
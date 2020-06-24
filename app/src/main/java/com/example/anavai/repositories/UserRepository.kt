package com.example.anavai.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.anavai.api_service.ApiService
import com.example.anavai.models.User
import com.example.anavai.utils.PreferenceProvider
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository(
    private val apiService: ApiService,
    private val preferences: PreferenceProvider
) {

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
                    if (response.isSuccessful) {
                        val token = JSONObject(response.body()!!.string())
                        preferences.setBearerToken(token.get("token").toString())
                        loginResponse.value = "success"
                    } else {
                        val error = JSONObject(response.errorBody()!!.string())
                        loginResponse.value = error.getString("error")
                    }
                }
            })

        return loginResponse
    }

    suspend fun getUserInfo(): User {
        return apiService.getUserInfo().await()
    }

    fun logout(){
        apiService.logout()
    }

    fun refreshToken() {
        apiService.refreshToken()
            .enqueue(object : Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    System.out.println(t.message)
                }

                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    if (response.isSuccessful) {
                        val token = JSONObject(response.body()!!.string())
                        preferences.setBearerToken(token.get("token").toString())
                    } else {
                        System.out.println(response.errorBody()!!.string())
                    }
                }
            })
    }

}
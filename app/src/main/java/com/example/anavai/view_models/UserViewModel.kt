package com.example.anavai.view_models

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.anavai.interfaces.LogoutListener
import com.example.anavai.models.User
import com.example.anavai.repositories.UserRepository

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {

    var logoutListener: LogoutListener? = null

    suspend fun getUserInfo(): User {
        return userRepository.getUserInfo()
    }

    fun logout(view: View) {
        userRepository.logout()
        logoutListener?.onSuccess()
    }

}
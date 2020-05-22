package com.example.anavai.view_models

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.anavai.interfaces.LoginListener
import com.example.anavai.repositories.UserRepository

class LoginViewModel(private val repository: UserRepository) : ViewModel() {

    var email: String? = null
    var password: String? = null
    var loginListener: LoginListener? = null

    fun attemptLogin(view: View) {
        loginListener?.onStarted()
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            loginListener?.onFailure("Invalid email or password")
            return
        }

        val loginResponse = repository.login(email!!, password!!)
        loginListener?.onSuccess(loginResponse)
    }

}
package com.example.anavai.utils

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

private const val BEARER_TOKEN = "BearerToken"

class PreferenceProvider(context: Context) {

    private val appContext = context.applicationContext

    private val preferences: SharedPreferences
        get() = PreferenceManager.getDefaultSharedPreferences(
            appContext
        )

    fun setBearerToken(token: String){
        preferences.edit().putString(BEARER_TOKEN, token).apply()
    }

    fun getBearerToken() :String? {
        return preferences.getString(BEARER_TOKEN, null)
    }

}
package com.example.anavai.models

import com.google.gson.annotations.SerializedName

class User(
    @SerializedName("full_name")
    val fullName: String,
    val image: String,
    val email: String
)
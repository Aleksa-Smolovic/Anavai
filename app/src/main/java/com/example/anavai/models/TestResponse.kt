package com.example.anavai.models


import com.google.gson.annotations.SerializedName

data class TestResponse(
    val completed: Boolean,
    val id: Int,
    val title: String,
    val userId: Int
)
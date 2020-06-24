package com.example.anavai.models

import android.widget.RatingBar
import com.google.gson.annotations.SerializedName

class Review(
    val image: String,
    val title: String,
    @SerializedName("created_at")
    val createdAt: String,
    //TODO override setter
    val rating: String,
    var text: String
) {

}
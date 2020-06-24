package com.example.anavai.utils

import android.content.Context
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.anavai.main.LOCAL_IP

fun ImageView.loadImage(url: String) {
    val imageUrl = if (url.contains("http")) url else LOCAL_IP + url
    Glide.with(context)
        .load(imageUrl)
        .into(this)
}

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}
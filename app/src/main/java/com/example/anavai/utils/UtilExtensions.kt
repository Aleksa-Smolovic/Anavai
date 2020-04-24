package com.example.anavai.utils

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImage(url:String){
    Glide.with(context)
        .load(url)
        .into(this)
}
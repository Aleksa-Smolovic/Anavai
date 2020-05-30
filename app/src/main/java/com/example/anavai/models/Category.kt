package com.example.anavai.models

import com.example.anavai.R
import kotlin.random.Random

val overlays: IntArray = intArrayOf(
    R.color.overlay_music,
    R.color.overlay_movies,
    R.color.overlay_books,
    R.color.overlay_games,
    R.color.overlay_series,
    R.color.overlay_anime
)

class Category(val id: Long, val name: String, val image: String, var overlay: Int?) {

    init {
        this.overlay = overlays[Random.nextInt(0, 5)]
    }

}
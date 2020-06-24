package com.example.anavai.models

import java.time.LocalDateTime

class Movie(
    val id: Long,
    val title: String,
    val image: String,
    val text: String,
    val rating: Float?,
    val releaseDate: String?
) {
}
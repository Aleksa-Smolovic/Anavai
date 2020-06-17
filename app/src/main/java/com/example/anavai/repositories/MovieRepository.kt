package com.example.anavai.repositories

import androidx.lifecycle.MutableLiveData
import com.example.anavai.api_service.ApiService
import com.example.anavai.models.Category
import com.example.anavai.models.Movie

class MovieRepository(
    private val apiService: ApiService
) {

    private var movies: List<Movie>? = null

    suspend fun getMovies(): MutableLiveData<List<Movie>> {
        movies = apiService.getAllMovies().await()
        val data: MutableLiveData<List<Movie>> = MutableLiveData()
        data.value = movies
        return data
    }

}
package com.example.anavai.repositories

import androidx.lifecycle.MutableLiveData
import com.example.anavai.api_service.ApiService
import com.example.anavai.models.Category
import com.example.anavai.models.Movie

class MovieRepository(
    private val apiService: ApiService
) {

    private var movies: List<Movie>? = null

    suspend fun getMovies(categoryId: Long): MutableLiveData<List<Movie>> {
        movies = apiService.getAllMovies(categoryId).await()
        val data: MutableLiveData<List<Movie>> = MutableLiveData()
        data.value = movies
        return data
    }

    suspend fun getMovie(movieId: Long): Movie{
        return apiService.getMovie(movieId).await()
    }

}
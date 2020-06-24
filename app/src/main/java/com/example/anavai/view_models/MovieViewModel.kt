package com.example.anavai.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.anavai.models.Movie
import com.example.anavai.repositories.MovieRepository


class MovieViewModel(private val repository: MovieRepository) : ViewModel() {

    private var movieLiveData: MutableLiveData<List<Movie>> = MutableLiveData()

    suspend fun getMovieList(categoryId: Long): LiveData<List<Movie>> {
        movieLiveData = repository.getMovies(categoryId)
        return movieLiveData
    }

    suspend fun getMovie(movieId: Long): Movie {
        return repository.getMovie(movieId)
    }

}
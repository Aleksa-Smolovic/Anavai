package com.example.anavai.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.anavai.models.Movie
import com.example.anavai.repositories.MovieRepository


class MovieViewModel(private val repository: MovieRepository) : ViewModel() {

    private var movieLiveData: MutableLiveData<List<Movie>> = MutableLiveData()

    suspend fun getMovieList(): LiveData<List<Movie>> {
        movieLiveData = repository.getMovies()
        return movieLiveData
    }

}
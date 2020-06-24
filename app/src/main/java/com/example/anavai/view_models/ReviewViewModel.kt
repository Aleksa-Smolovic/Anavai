package com.example.anavai.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.anavai.models.Review
import com.example.anavai.repositories.ReviewRepository


class ReviewViewModel(private val repository: ReviewRepository) : ViewModel() {

    private var reviewLiveData: MutableLiveData<List<Review>> = MutableLiveData()

    suspend fun getReviews(movieId: Long): LiveData<List<Review>> {
        reviewLiveData = repository.getReviews(movieId)
        return reviewLiveData
    }

}
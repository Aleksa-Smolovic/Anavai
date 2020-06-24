package com.example.anavai.repositories

import androidx.lifecycle.MutableLiveData
import com.example.anavai.api_service.ApiService
import com.example.anavai.models.Category
import com.example.anavai.models.Review

class ReviewRepository(private val apiService: ApiService) {

    private var reviews: List<Review>? = null

    suspend fun getReviews(movieId: Long): MutableLiveData<List<Review>> {
        reviews = apiService.getReviews(movieId).await()
        val data: MutableLiveData<List<Review>> = MutableLiveData()
        data.value = reviews
        return data
    }

}
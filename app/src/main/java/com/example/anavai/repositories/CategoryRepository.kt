package com.example.anavai.repositories

import androidx.lifecycle.MutableLiveData
import com.example.anavai.api_service.ApiService
import com.example.anavai.models.Category

class CategoryRepository(
    private val apiService: ApiService
) {

    private var categories: List<Category>? = null

    suspend fun getCategories(): MutableLiveData<List<Category>> {
        categories = apiService.getCategories().await()
        val data: MutableLiveData<List<Category>> = MutableLiveData()
        data.value = categories
        return data
    }

}
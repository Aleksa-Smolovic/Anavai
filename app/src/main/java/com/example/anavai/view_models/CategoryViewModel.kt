package com.example.anavai.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.anavai.models.Category
import com.example.anavai.repositories.CategoryRepository

class CategoryViewModel(private val categoryRepository: CategoryRepository) : ViewModel() {

    private var categoryLiveData: MutableLiveData<List<Category>> = MutableLiveData()

    suspend fun getCategories(): LiveData<List<Category>> {
        System.out.println("Uslo 05")
        categoryLiveData = categoryRepository.getCategories()
        return categoryLiveData
    }

}
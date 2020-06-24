package com.example.anavai.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.anavai.models.Blog
import com.example.anavai.models.Category
import com.example.anavai.repositories.BlogRepository

class BlogViewModel(private val blogRepository: BlogRepository) : ViewModel() {

    private var blogLiveData: MutableLiveData<List<Blog>> = MutableLiveData()

    suspend fun getBlogs(): LiveData<List<Blog>> {
        blogLiveData = blogRepository.getBlogs()
        return blogLiveData
    }

}
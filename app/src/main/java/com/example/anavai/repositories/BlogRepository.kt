package com.example.anavai.repositories

import androidx.lifecycle.MutableLiveData
import com.example.anavai.api_service.ApiService
import com.example.anavai.models.Blog
import com.example.anavai.models.Category

class BlogRepository(
    private val apiService: ApiService
) {

    private var blogs: List<Blog>? = null

    suspend fun getBlogs(): MutableLiveData<List<Blog>> {
        blogs = apiService.getBlogs().await()
        val data: MutableLiveData<List<Blog>> = MutableLiveData()
        data.value = blogs
        return data
    }

}
package com.example.anavai.repositories

import androidx.lifecycle.MutableLiveData
import com.example.anavai.api_service.ApiService
import com.example.anavai.models.Actor

class ActorRepository(
    private val apiService: ApiService
) {

    private var actors: List<Actor>? = null

    suspend fun getActors(movieId: Long): MutableLiveData<List<Actor>> {
        actors = apiService.getActors(movieId).await()
        val data: MutableLiveData<List<Actor>> = MutableLiveData()
        data.value = actors
        return data
    }

}
package com.example.anavai.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.anavai.models.Actor
import com.example.anavai.models.Category
import com.example.anavai.repositories.ActorRepository

class ActorViewModel(private val actorRepository: ActorRepository) : ViewModel() {

    private var actorLiveData: MutableLiveData<List<Actor>> = MutableLiveData()

    suspend fun getActors(movieId: Long): LiveData<List<Actor>> {
        actorLiveData = actorRepository.getActors(movieId)
        return actorLiveData
    }

}
package com.example.anavai.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.anavai.models.Media
import com.example.anavai.repositories.MediaRepository
import com.example.anavai.models.TestResponse
import com.example.anavai.utils.PreferenceProvider


class MediaViewModel (
    private val repository: MediaRepository,
    private val preferences: PreferenceProvider
) : ViewModel() {

    private var mediaLiveData: MutableLiveData<List<Media>> = MutableLiveData()

    public fun getMediaList(): LiveData<List<Media>>{
        return mediaLiveData
    }

    suspend fun getTest():List<TestResponse>{
        return repository.getTest()
    }

    init {
        mediaLiveData = repository.getMediaList()
    }

}
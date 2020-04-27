package com.example.anavai.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.anavai.models.MediaInstance
import com.example.anavai.repositories.MediaInstanceRepository


class MediaInstanceViewModel(private val repository: MediaInstanceRepository) :ViewModel() {

    private var mediaInstanceLiveData: MutableLiveData<List<MediaInstance>> = MutableLiveData()

    public fun getMediaInstanceList(): LiveData<List<MediaInstance>>{
        return mediaInstanceLiveData
    }

    init {
        mediaInstanceLiveData = repository.getMediaInstanceList()
    }

}
package com.example.anavai.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.anavai.models.MediaInstance
import com.example.anavai.Repositories.MediaInstanceRepository


class MediaInstanceViewModel :ViewModel() {

    private var mediaInstanceLiveData: MutableLiveData<List<MediaInstance>> = MutableLiveData()
    private var repository:MediaInstanceRepository = MediaInstanceRepository

    public fun getMediaInstanceList(): LiveData<List<MediaInstance>>{
        return mediaInstanceLiveData
    }

    init {
        mediaInstanceLiveData = repository.getMediaInstanceList()
    }

}
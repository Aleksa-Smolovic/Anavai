package com.example.anavai.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.anavai.models.Media
import com.example.anavai.Repositories.MediaRepository


class MediaViewModel :ViewModel() {

    private var mediaLiveData: MutableLiveData<List<Media>> = MutableLiveData()
    private var repository:MediaRepository = MediaRepository

    public fun getMediaList(): LiveData<List<Media>>{
        return mediaLiveData
    }

    init {
        mediaLiveData = repository.getMediaList()
    }

}
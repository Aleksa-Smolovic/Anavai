package com.example.anavai.Repositories

import androidx.lifecycle.MutableLiveData
import com.example.anavai.models.MediaInstance

object MediaInstanceRepository{

    private var mediaInstanceList = ArrayList<MediaInstance>()

    public fun getMediaInstanceList(): MutableLiveData<List<MediaInstance>> {
        populateMediaInstanceList()
        val data: MutableLiveData<List<MediaInstance>> = MutableLiveData()
        data.value = mediaInstanceList
        return data
    }

    private fun populateMediaInstanceList(){
        for (i in 0..10) {
            mediaInstanceList.add(
                MediaInstance(
                    "Rick and Morty",
                    1,
                    "https://images4.alphacoders.com/102/thumb-1920-1028306.png",
                    "https://images4.alphacoders.com/102/thumb-1920-1028306.png"
                )
            )
        }
    }

}
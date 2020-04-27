package com.example.anavai.repositories

import androidx.lifecycle.MutableLiveData
import com.example.anavai.api_service.ApiService
import com.example.anavai.models.Comment

class CommentRepository(private val apiService: ApiService){

    private var commentList = ArrayList<Comment>()

    public fun getCommentList(): MutableLiveData<List<Comment>> {
        populateCommentList()
        val data: MutableLiveData<List<Comment>> = MutableLiveData()
        data.value = commentList
        return data
    }

    private fun populateCommentList(){
        for (i in 0..10) {
            commentList.add(
                Comment(
                    "https://images4.alphacoders.com/102/thumb-1920-1028306.png"
                )
            )
        }
    }

}
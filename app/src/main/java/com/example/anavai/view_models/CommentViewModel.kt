package com.example.anavai.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.anavai.models.Comment
import com.example.anavai.repositories.CommentRepository


class CommentViewModel(private val repository: CommentRepository) :ViewModel() {

    private var commentLiveData: MutableLiveData<List<Comment>> = MutableLiveData()

    public fun getCommentList(): LiveData<List<Comment>>{
        return commentLiveData
    }

    init {
        commentLiveData = repository.getCommentList()
    }

}
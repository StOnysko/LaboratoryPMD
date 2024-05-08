package com.example.laboratory.application.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.laboratory.application.data.Post

class PostModel : ViewModel() {
    private val selectedPost = MutableLiveData<Post>()

    fun selectPost(post: Post) {
        selectedPost.value = post
    }

    fun getSelectedPost(): LiveData<Post> {
        return selectedPost
    }

    fun getPostId(): Int {
        return selectedPost.value?.id ?: -1
    }
}
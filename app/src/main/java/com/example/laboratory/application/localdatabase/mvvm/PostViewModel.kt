package com.example.laboratory.application.localdatabase.mvvm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.laboratory.application.data.Post
import com.example.laboratory.application.localdatabase.database.LocalDatabase
import com.example.laboratory.application.localdatabase.repository.PostRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PostViewModel(application: Application) : AndroidViewModel(application = application) {

    private val repository: PostRepository
    val readData: LiveData<List<Post>>

    init {
        val postDao = LocalDatabase.createLocalDatabase(application).postDao()
        repository = PostRepository(postDao = postDao)
        readData = repository.readPosts
    }

    fun deletePost(post: Post) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.removePost(post = post)
        }
    }

    fun createPost(post: Post) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertPost(post = post)
        }
    }

    fun editPost(post: Post) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updatePost(post = post)
        }
    }
}
package com.example.laboratory.application.localdatabase.mvvm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.laboratory.application.data.Comment
import com.example.laboratory.application.localdatabase.database.LocalDatabase
import com.example.laboratory.application.localdatabase.repository.CommentRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CommentViewModel(application: Application) : AndroidViewModel(application = application) {

    private val repository: CommentRepository
    val getData: LiveData<List<Comment>>

    init {
        val commentDao = LocalDatabase.createLocalDatabase(application).commentDao()
        repository = CommentRepository(commentDao)
        getData = repository.getCommentsById
    }

    suspend fun createComment(comment: Comment) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertComment(comment = comment)
        }
    }

    suspend fun deleteComment(comment: Comment) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.removeComment(comment = comment)
        }
    }

    suspend fun editComment(comment: Comment) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateComment(comment = comment)
        }
    }

    fun getCommentsByPostId(id: Int): LiveData<List<Comment>> {
        return repository.getCommentsFromPost(id)
    }
}
package com.example.laboratory.application.localdatabase.repository

import androidx.lifecycle.LiveData
import com.example.laboratory.application.data.Comment
import com.example.laboratory.application.interfaces.CommentDao
import com.example.laboratory.application.localdatabase.database.LocalDatabase

class CommentRepository(private val commentDao: CommentDao) {

    val getCommentsById: LiveData<List<Comment>> = commentDao.getAllComments()

    fun getCommentsFromPost(id: Int): LiveData<List<Comment>> {
        return commentDao.getCommentsFromPost(id)
    }

    suspend fun insertComment(comment: Comment) {
        commentDao.insertComment(comment = comment)
    }

    suspend fun removeComment(comment: Comment) {
        commentDao.removeComment(comment = comment)
    }

    suspend fun updateComment(comment: Comment) {
        commentDao.updateComment(comment = comment)
    }
}
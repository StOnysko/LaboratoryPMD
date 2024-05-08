package com.example.laboratory.application.interfaces

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.laboratory.application.data.Comment

@Dao
interface CommentDao {

    @Query("SELECT * FROM comments ORDER BY id ASC")
    fun getAllComments(): LiveData<List<Comment>>

    @Query("SELECT * FROM comments WHERE post_id = :id")
    fun getCommentsFromPost(id: Int): LiveData<List<Comment>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertComment(comment: Comment)

    @Delete
    suspend fun removeComment(comment: Comment)

    @Update
    suspend fun updateComment(comment: Comment)
}
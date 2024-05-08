package com.example.laboratory.application.interfaces

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.laboratory.application.data.Post

@Dao
interface PostDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertPostToDatabase(post: Post)

    @Query("SELECT * FROM post ORDER BY id ASC")
    fun getPostsListFromDatabase(): LiveData<List<Post>>

    @Update
    suspend fun updatePostFromDatabase(post: Post)

    @Delete
    suspend fun deletePostFromDatabase(post: Post)
}
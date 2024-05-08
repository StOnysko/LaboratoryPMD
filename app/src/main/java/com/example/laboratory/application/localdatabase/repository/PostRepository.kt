package com.example.laboratory.application.localdatabase.repository

import androidx.lifecycle.LiveData
import com.example.laboratory.application.data.Post
import com.example.laboratory.application.interfaces.PostDao

class PostRepository(private val postDao: PostDao) {
    val readPosts: LiveData<List<Post>> = postDao.getPostsListFromDatabase()

    suspend fun insertPost(post: Post) {
        postDao.insertPostToDatabase(post = post)
    }

    suspend fun updatePost(post: Post) {
        postDao.updatePostFromDatabase(post = post)
    }

    suspend fun removePost(post: Post) {
        postDao.deletePostFromDatabase(post = post)
    }
}
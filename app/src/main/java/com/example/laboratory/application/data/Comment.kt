package com.example.laboratory.application.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "comments",
    foreignKeys = [
        ForeignKey(
            entity = Post::class,
            parentColumns = ["id"],
            childColumns = ["post_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)

data class Comment(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "post_id") val postId: Int,
    @ColumnInfo(name = "text") val postText: String
)
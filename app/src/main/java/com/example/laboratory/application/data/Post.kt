package com.example.laboratory.application.data

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Post(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "image") val postImage: Bitmap,
    @ColumnInfo(name = "header") val postHeader: String,
    @ColumnInfo(name = "description") val postDescription: String
)
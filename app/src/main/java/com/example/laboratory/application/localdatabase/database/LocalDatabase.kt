package com.example.laboratory.application.localdatabase.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.laboratory.application.converter.BitmapConverter
import com.example.laboratory.application.data.Comment
import com.example.laboratory.application.data.Post
import com.example.laboratory.application.interfaces.CommentDao
import com.example.laboratory.application.interfaces.PostDao

@Database(entities = [Post::class, Comment::class], exportSchema = false, version = 1)
@TypeConverters(BitmapConverter::class)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun postDao(): PostDao
    abstract fun commentDao(): CommentDao

    companion object {
        private var INSTANCE: LocalDatabase? = null

        fun createLocalDatabase(context: Context): LocalDatabase {
            val temporalDb = INSTANCE
            if (temporalDb != null) {
                return temporalDb
            }
            synchronized(this) {
                val database =
                    Room.databaseBuilder(context, LocalDatabase::class.java, "database.db").build()
                INSTANCE = database
                return database
            }
        }
    }
}
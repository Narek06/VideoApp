package com.example.videoapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Videos::class], version = 1)
abstract class VideoDatabase : RoomDatabase() {
    abstract fun VideoDao(): VideoDao

    companion object {
        private var INSTANCE: VideoDatabase? = null
        fun getDatabase(context: Context): VideoDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    VideoDatabase::class.java,
                    "article_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
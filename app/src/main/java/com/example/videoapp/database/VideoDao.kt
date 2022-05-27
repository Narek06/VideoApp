package com.example.videoapp.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface VideoDao {
    @Query("SELECT * FROM articles;")
    fun getAll(): List<Videos>

    @Insert
    fun insertAll(vararg arts: Videos?)

    @Query("SELECT NOT EXISTS(SELECT * FROM articles WHERE url = :url )")
    fun isNotExists(url:String?):Boolean

    @Delete
    fun delete(art: Videos)
}
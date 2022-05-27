package com.example.videoapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "articles")
data class Videos(
    @ColumnInfo(name = "Type") val type: String?,
    @ColumnInfo(name = "url") val url: String?,
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }
}
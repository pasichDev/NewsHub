package com.pasichdev.newshub.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "saved")
data class Saved(
    @PrimaryKey(autoGenerate = false) @ColumnInfo(name = "id") var id: Int,
    @field:SerializedName("author") val author: String? = null,

    @field:SerializedName("title") val title: String,

    @field:SerializedName("description") val description: String? = null,

    @field:SerializedName("url") val url: String,

    @field:SerializedName("urlToImage") val urlToImage: String,

    @field:SerializedName("publishedAt") val publishedAt: String
)
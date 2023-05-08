package com.pasichdev.newshub.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "saved")
data class News(
    @field:SerializedName("source") val source: Source,

    @field:SerializedName("author") val author: String,

    @field:SerializedName("title") val title: String,

    @field:SerializedName("description") val description: String? = null,

    @PrimaryKey(autoGenerate = false) @field:SerializedName("url") val url: String,

    @field:SerializedName("urlToImage") val urlToImage: String,

    @field:SerializedName("publishedAt") val publishedAt: String
)
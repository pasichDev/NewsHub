package com.pasichdev.newshub.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "saved")
data class Saved(

    @PrimaryKey(autoGenerate = false) @field:SerializedName("title") val title: String,
    val author: String? = null,

    val description: String = "",

    val url: String,

    val urlToImage: String,

    val publishedAt: String
)
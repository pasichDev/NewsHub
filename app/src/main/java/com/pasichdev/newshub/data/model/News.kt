package com.pasichdev.newshub.data.model

import com.google.gson.annotations.SerializedName

data class News(
    @field:SerializedName("source") val source: Source,

    @field:SerializedName("author") val author: String? = null,

    @field:SerializedName("title") val title: String,

    @field:SerializedName("description") val description: String? = null,

    @field:SerializedName("url") val url: String,

    @field:SerializedName("urlToImage") val urlToImage: String,

    @field:SerializedName("publishedAt") val publishedAt: String
)
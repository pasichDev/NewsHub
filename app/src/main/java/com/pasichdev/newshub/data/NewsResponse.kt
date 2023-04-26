package com.pasichdev.newshub.data

import com.google.gson.annotations.SerializedName
import com.pasichdev.newshub.data.model.News

data class NewsResponse(

    @field:SerializedName("status")
    val status: String? = null,

    @field:SerializedName("count")
    val count: String? = null,

    @field:SerializedName("articles")
    val results: List<News> = listOf(),
)
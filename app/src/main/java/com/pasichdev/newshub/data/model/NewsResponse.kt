package com.pasichdev.newshub.data.model

import com.google.gson.annotations.SerializedName

data class NewsResponse(

    @field:SerializedName("status")
    val status: String? = null,

    @field:SerializedName("count")
    val count: String? = null,

    @field:SerializedName("next")
    val next: String? = null,

    @field:SerializedName("articles")
    val results: List<News> = listOf(),
)
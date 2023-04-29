package com.pasichdev.newshub.data.services

import com.pasichdev.newshub.data.model.NewsResponse
import com.pasichdev.newshub.utils.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("everything")
    suspend fun getBusinessNews(
        @Query("apiKey") key: String = API_KEY,
        @Query("language") country: String,
        @Query("q") category: String,
        @Query("pageSize") size: Int = 5
    ): NewsResponse

}
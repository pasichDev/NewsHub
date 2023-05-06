package com.pasichdev.newshub.data.network

import com.pasichdev.newshub.data.model.NewsResponse
import com.pasichdev.newshub.utils.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("top-headlines")
    suspend fun getHeadlinesCategory(
        @Query("apiKey") key: String = API_KEY,
        @Query("country") country: String,
        @Query("category") category: String,
        @Query("pageSize") pageSize: Int = 20,
        @Query("page") page: Int = 1
    ): NewsResponse


}
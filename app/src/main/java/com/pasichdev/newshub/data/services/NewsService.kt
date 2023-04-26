package com.pasichdev.newshub.data.services

import com.pasichdev.newshub.data.NewsResponse
import com.pasichdev.newshub.data.model.News
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NewsService {

    @GET("everything")
    suspend fun getBusinessNews(
        @Query("apiKey") key: String = "b626d1bef6724337a0a591d9ea9b4165",
        @Query("language") country: String,
        @Query("q") category: String,
        @Query("pageSize") size: Int = 5
    ): NewsResponse

    @GET("games/{id}")
    suspend fun getGamesDetail(
        @Path("id") id: Int,
        @Query("key") key: String = "b626d1bef6724337a0a591d9ea9b4165",
    ): News

    companion object {
        private var retrofitService: NewsService? = null
        fun getInstance(): NewsService {
            if (retrofitService == null) {
                val client = OkHttpClient
                    .Builder()
                    .addInterceptor(
                        HttpLoggingInterceptor()
                            .setLevel(HttpLoggingInterceptor.Level.BODY)
                    )
                    .build()
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://newsapi.org/v2/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()
                retrofitService = retrofit.create(NewsService::class.java)
            }
            return retrofitService!!
        }
    }
}
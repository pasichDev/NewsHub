package com.pasichdev.newshub.data.repository

import com.pasichdev.newshub.Response
import com.pasichdev.newshub.data.NewsResponse
import kotlinx.coroutines.flow.Flow


interface NewsRepository {
    fun getBusinessNews(category: String): Flow<Response<NewsResponse>>
    //  fun getDetailGames(category: String): Flow<Response<News>>
}
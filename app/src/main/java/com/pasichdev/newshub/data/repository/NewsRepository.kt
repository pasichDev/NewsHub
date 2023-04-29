package com.pasichdev.newshub.data.repository

import com.pasichdev.newshub.data.model.NewsResponse
import com.pasichdev.newshub.utils.Response
import kotlinx.coroutines.flow.Flow


interface NewsRepository {
    fun getBusinessNews(category: String): Flow<Response<NewsResponse>>
    //  fun getDetailGames(category: String): Flow<Response<News>>
}
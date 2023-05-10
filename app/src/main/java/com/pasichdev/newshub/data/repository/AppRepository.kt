package com.pasichdev.newshub.data.repository

import androidx.paging.PagingData
import com.pasichdev.newshub.data.model.News
import kotlinx.coroutines.flow.Flow


interface AppRepository {
    suspend fun savedNews(news: News)
    suspend fun unSavedNews(news: News)
    fun getCategoryNews(category: String, country: String): Flow<PagingData<News>>
    fun getAllSavedNews(): Flow<List<News>>
}
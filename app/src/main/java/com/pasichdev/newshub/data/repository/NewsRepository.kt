package com.pasichdev.newshub.data.repository

import androidx.paging.PagingData
import com.pasichdev.newshub.data.model.News
import kotlinx.coroutines.flow.Flow


interface NewsRepository {
    fun savedNews(news: News)
    fun getCategoryNews(category: String, country: String): Flow<PagingData<News>>
}
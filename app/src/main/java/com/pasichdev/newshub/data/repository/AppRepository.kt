package com.pasichdev.newshub.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.pasichdev.newshub.data.model.News
import kotlinx.coroutines.flow.Flow


interface AppRepository {
    fun savedNews(news: News)
    fun unSavedNews(news: News)
    fun getCategoryNews(category: String, country: String): Flow<PagingData<News>>

    fun getAllSavedNews(): LiveData<List<News>>
}
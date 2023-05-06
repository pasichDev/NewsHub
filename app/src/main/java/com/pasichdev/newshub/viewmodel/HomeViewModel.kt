package com.pasichdev.newshub.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.pasichdev.newshub.data.model.News
import com.pasichdev.newshub.data.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import hilt_aggregated_deps.data.NewsCategory
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    var newsRepository: NewsRepository,
) : ViewModel() {

    val categoryNews = NewsCategory.tagsNews
    val categoryNewsIndex = mutableStateOf(0)


    fun loadCategoryNews(category: String, country: String): Flow<PagingData<News>> {
        return newsRepository.getCategoryNews(category, country).cachedIn(viewModelScope)

    }

    fun refreshListNews() {

    }

    fun savedNews(news: News) {

    }

    fun unSavedNews(news: News) {

    }
}
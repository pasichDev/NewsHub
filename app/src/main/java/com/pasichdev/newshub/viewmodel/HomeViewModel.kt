package com.pasichdev.newshub.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.pasichdev.newshub.data.data.NewsCategory
import com.pasichdev.newshub.data.model.News
import com.pasichdev.newshub.data.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    var appRepository: AppRepository,
) : ViewModel() {

    val categoryNews = NewsCategory.tagsNews
    val categoryNewsIndex = mutableStateOf(0)
    val allSavedNews: LiveData<List<News>> = appRepository.getAllSavedNews()


    fun loadCategoryNews(category: String, country: String): Flow<PagingData<News>> {
        return appRepository.getCategoryNews(category, country).cachedIn(viewModelScope)

    }

    fun refreshListNews() {

    }

    fun savedNews(news: News, saved: Boolean) {
        if (saved) appRepository.unSavedNews(news)
        else appRepository.savedNews(news)
    }

}
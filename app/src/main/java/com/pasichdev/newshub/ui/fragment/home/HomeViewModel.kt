package com.pasichdev.newshub.ui.fragment.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.pasichdev.newshub.R
import com.pasichdev.newshub.data.NewsState
import com.pasichdev.newshub.data.model.News
import com.pasichdev.newshub.data.repository.AppRepository
import com.pasichdev.newshub.utils.CountryHeadLines
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private var appRepository: AppRepository,
) : ViewModel() {
    val tagsNewsIndex = listOf(
        "Business",
        "Entertainment",
        "General",
        "Health",
        "Science",
        "Sports",
        "Technology"
    )

    val categoryNews = listOf(
        R.string.BusinessCat,
        R.string.EntertainmentCat,
        R.string.GeneralCat,
        R.string.HealthCat,
        R.string.ScienceCat,
        R.string.SportsCat,
        R.string.TechnologyCat,
    )

    //saved news
    private val _savedNews = MutableStateFlow(emptyList<News>())
    val savedNews = _savedNews.asStateFlow()

    //news
    private val _news = mutableStateOf(NewsState())
    val news: State<NewsState> = _news
    val categoryNewsIndex = mutableStateOf(0)

    init {
        getSavedNews()
        _news.value = NewsState(assets = getNews().map { pagingData ->
            pagingData.map {
                it
            }
        })
    }


    private fun getNews(): Flow<PagingData<News>> {
        return appRepository.getCategoryNews(
            "Business", CountryHeadLines.getCountryHeadLines(
                Locale.getDefault().country
            )
        ).cachedIn(viewModelScope)
    }


    private fun getSavedNews() {
        viewModelScope.launch {
            appRepository.getAllSavedNews().flowOn(Dispatchers.IO).collect { news: List<News> ->
                _savedNews.update { news }
            }
        }
    }





    fun savedNews(news: News, saved: Boolean) {
        if (saved) {
            viewModelScope.launch(Dispatchers.IO) {
                appRepository.unSavedNews(news)

            }
        } else {
            viewModelScope.launch(Dispatchers.IO) {
                appRepository.savedNews(news)

            }
        }
    }

}
package com.pasichdev.newshub.ui.fragment.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.pasichdev.newshub.R
import com.pasichdev.newshub.data.model.News
import com.pasichdev.newshub.data.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
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

    private val _newsState = MutableStateFlow<Flow<PagingData<News>>>(emptyFlow())
    val newsState: StateFlow<Flow<PagingData<News>>> get() = _newsState
    private val _savedNews = MutableStateFlow(emptyList<News>())
    val savedNews = _savedNews.asStateFlow()


    val categoryNewsIndex = mutableStateOf(0)


    init {
        getSavedNews()
    }


    private fun getSavedNews() {
        viewModelScope.launch { //this: CoroutineScope
            appRepository.getAllSavedNews().flowOn(Dispatchers.IO).collect { news: List<News> ->
                _savedNews.update { news }
            }
        }
    }


    fun loadCategoryNews(category: String, country: String): Flow<PagingData<News>> {
        return appRepository.getCategoryNews(category, country).cachedIn(viewModelScope)

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
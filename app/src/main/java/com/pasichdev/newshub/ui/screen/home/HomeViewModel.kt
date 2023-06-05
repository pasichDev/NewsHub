package com.pasichdev.newshub.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.pasichdev.newshub.data.model.News
import com.pasichdev.newshub.data.repository.AppRepository
import com.pasichdev.newshub.utils.CountryHeadLines
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private var appRepository: AppRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state.asStateFlow()

    init {
        observeNews()
    }

    private fun observeNews() {
        state.distinctUntilChangedBy { homeState ->
            homeState.categoryIndex
        }.map {


            _state.update { homeState ->
                homeState.copy(
                    news = getNews(homeState.tagsNewsIndex[homeState.tabIndex]),
                    savedNews = emptyList() // List<News>

                )
            }

        }.launchIn(viewModelScope)

    }


    fun onCategoryChanged(index: Int) {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    categoryIndex = index,
                )
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

    private fun getNews(category: String): Flow<PagingData<News>> {
        return appRepository.getCategoryNews(
            category, CountryHeadLines.getCountryHeadLines(
                Locale.getDefault().country
            )
        ).cachedIn(viewModelScope)
    }

}
package com.pasichdev.newshub.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pasichdev.newshub.data.data.NewsCategory
import com.pasichdev.newshub.data.model.NewsResponse
import com.pasichdev.newshub.data.repository.NewsRepository
import com.pasichdev.newshub.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    var newsRepository: NewsRepository,
) : ViewModel() {

    val categoryNews = NewsCategory.tagsNews
    val categoryNewsIndex = mutableStateOf(0)


    private val responseState = mutableStateOf<Response<NewsResponse>>(Response.Success(null))
    val newsState: State<Response<NewsResponse>> = responseState


    fun getNewsCategory(category: String) {
        viewModelScope.launch {
            newsRepository.getBusinessNews(category).collect { response ->
                responseState.value = response
            }
        }
    }

}
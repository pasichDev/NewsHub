package com.pasichdev.newshub.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pasichdev.newshub.Response
import com.pasichdev.newshub.data.NewsResponse
import com.pasichdev.newshub.data.repository.NewsRepository
import com.pasichdev.newshub.data.repository.NewsRepositoryImpl
import kotlinx.coroutines.launch

class HomeViewModel constructor(
    var newsRepository: NewsRepository = NewsRepositoryImpl(),
) : ViewModel() {


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
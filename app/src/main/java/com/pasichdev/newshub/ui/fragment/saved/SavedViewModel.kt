package com.pasichdev.newshub.ui.fragment.saved

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pasichdev.newshub.data.model.News
import com.pasichdev.newshub.data.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedViewModel @Inject constructor(
    var appRepository: AppRepository,
) : ViewModel() {
    private val _state = MutableStateFlow(SavedState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch { //this: CoroutineScope
            appRepository.getAllSavedNews().flowOn(Dispatchers.IO).collect { news: List<News> ->
                _state.value.savedNews = news
            }
        }
    }


    fun deleteSavedNews(news: News) {
        viewModelScope.launch(Dispatchers.IO) {
            appRepository.unSavedNews(news)
        }
    }


}
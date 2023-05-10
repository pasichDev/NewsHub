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
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedViewModel @Inject constructor(
    var appRepository: AppRepository,
) : ViewModel() {
    private val _savedNews = MutableStateFlow(emptyList<News>())
    val savedNews = _savedNews.asStateFlow()

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


}
package com.pasichdev.newshub.ui.screen.viewNews

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pasichdev.newshub.data.model.News
import com.pasichdev.newshub.data.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ViewNewsViewModel @Inject constructor(
    private var appRepository: AppRepository,
) : ViewModel() {

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
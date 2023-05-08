package com.pasichdev.newshub.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.pasichdev.newshub.data.model.News
import com.pasichdev.newshub.data.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SavedViewModel @Inject constructor(
    var appRepository: AppRepository,
) : ViewModel() {


    val allSavedNews: LiveData<List<News>> = appRepository.getAllSavedNews()


}
package com.pasichdev.newshub.viewmodel

import androidx.lifecycle.ViewModel
import com.pasichdev.newshub.data.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(
    var newsRepository: NewsRepository,
) : ViewModel()
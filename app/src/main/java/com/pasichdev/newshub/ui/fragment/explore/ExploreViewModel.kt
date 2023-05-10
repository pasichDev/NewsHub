package com.pasichdev.newshub.ui.fragment.explore

import androidx.lifecycle.ViewModel
import com.pasichdev.newshub.data.repository.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(
    var appRepository: AppRepository,
) : ViewModel()
package com.pasichdev.newshub.ui.fragment.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.paging.PagingData
import com.pasichdev.newshub.data.model.News
import kotlinx.coroutines.flow.Flow

data class HomeState(
    val news: Flow<PagingData<News>>? = null,
    val savedNews: List<News>? = null,
    val categoryNewsIndex: MutableState<Int> = mutableStateOf(0)
)
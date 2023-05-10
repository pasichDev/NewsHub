package com.pasichdev.newshub.ui.fragment.home

import androidx.paging.PagingData
import com.pasichdev.newshub.data.model.News
import kotlinx.coroutines.flow.Flow

data class HomeState(
    var news: Flow<PagingData<News>>? = null,
    var savedNews: List<News>? = null,
    var categoryIndex: Int = 0
)
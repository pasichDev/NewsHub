package com.pasichdev.newshub.data

import androidx.paging.PagingData
import com.pasichdev.newshub.data.model.News
import kotlinx.coroutines.flow.Flow

data class NewsState(
    val assets: Flow<PagingData<News>>? = null
)
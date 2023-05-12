package com.pasichdev.newshub.ui.screen.home

import androidx.paging.PagingData
import com.pasichdev.newshub.R
import com.pasichdev.newshub.data.model.News
import kotlinx.coroutines.flow.Flow

data class HomeState(
    var news: Flow<PagingData<News>>? = null,
    var savedNews: List<News>? = null,
    var categoryIndex: Int = 0,
    var tabIndex: Int = 0,
    val tagsNewsIndex: List<String> = listOf(
        "Business",
        "Entertainment",
        "General",
        "Health",
        "Science",
        "Sports",
        "Technology"
    ),
    val categoryNews: List<Int> = listOf(
        R.string.BusinessCat,
        R.string.EntertainmentCat,
        R.string.GeneralCat,
        R.string.HealthCat,
        R.string.ScienceCat,
        R.string.SportsCat,
        R.string.TechnologyCat,
    )
)
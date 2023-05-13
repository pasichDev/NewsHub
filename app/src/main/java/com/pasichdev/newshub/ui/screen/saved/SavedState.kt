package com.pasichdev.newshub.ui.screen.saved

import com.pasichdev.newshub.data.model.News

data class SavedState(
    var savedNews: List<News>? = emptyList()
)
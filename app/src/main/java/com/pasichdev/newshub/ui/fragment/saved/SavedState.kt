package com.pasichdev.newshub.ui.fragment.saved

import com.pasichdev.newshub.data.model.News

data class SavedState(
    var savedNews: List<News>? = null
)
package com.pasichdev.newshub.ui.screen

import com.pasichdev.newshub.data.model.News

interface ClickNews {
    fun clickNews(urlNews: String, saved: Boolean)
    fun savedNews(news: News, saved: Boolean)
}
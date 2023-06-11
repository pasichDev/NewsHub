package com.pasichdev.newshub.ui.screen

import com.pasichdev.newshub.data.model.News

interface ClickNews {
    fun clickNews(news: News, saved: Boolean)
    fun savedNews(news: News, saved: Boolean)
}
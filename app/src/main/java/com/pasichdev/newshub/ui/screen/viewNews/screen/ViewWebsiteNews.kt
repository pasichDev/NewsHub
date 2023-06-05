package com.pasichdev.newshub.ui.screen.viewNews.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState


@Composable
fun WebViewWithNews(
    newsUrl: String,
    modifier: Modifier
) {

    val webViewState = rememberWebViewState(newsUrl)
    WebView(
        modifier = modifier,
        state = webViewState,
        onCreated = { it.settings.javaScriptEnabled = true }
    )
}




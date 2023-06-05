package com.pasichdev.newshub.ui.screen.viewNews.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState
import com.pasichdev.newshub.ui.screen.viewNews.ViewNewsViewModel


@Composable
fun WebViewWithNews(
    newsUrl: String,
    modifier: Modifier,
    vnViewModel: ViewNewsViewModel = hiltViewModel()
) {

    val webViewState = rememberWebViewState(newsUrl)
    WebView(
        modifier = modifier,
        state = webViewState,
        onCreated = { it.settings.javaScriptEnabled = true }
    )
}




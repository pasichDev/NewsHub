package com.pasichdev.newshub.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState

@Composable
fun ViewWebsiteNews(
    newsUrl: String, modifier: Modifier
) {
    val state = rememberWebViewState(newsUrl)
    WebView(
        state = state,
        modifier = modifier


        // onCreated = { it.settings.javaScriptEnabled = true }
    )

    /* AndroidView(factory = {
         WebView(it).apply {
             layoutParams = ViewGroup.LayoutParams(
                 ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
             )
             webViewClient = WebViewClient()
             loadUrl(newsUrl)
         }
     }, update = {
         it.loadUrl(newsUrl)
     })

     */
}



package com.pasichdev.newshub.ui.components

import android.graphics.Bitmap
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView


@Composable
fun WebViewWithNews(newsUrl: String, modifier: Modifier) {
    var isLoading by remember { mutableStateOf(true) }
    var error by remember { mutableStateOf(false) }
    var refresh by remember { mutableStateOf(true) }

    AndroidView(factory = { context ->
        WebView(context).apply {
            webViewClient = object : WebViewClient() {
                override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                    isLoading = true
                    super.onPageStarted(view, url, favicon)
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    isLoading = false
                    super.onPageFinished(view, url)
                }


                override fun onReceivedError(
                    view: WebView?, request: WebResourceRequest?, errors: WebResourceError?
                ) {
                    error = true
                    super.onReceivedError(view, request, errors)
                }

            }
        }
    }, update = {
        if (refresh) {
            it.loadUrl(newsUrl)
            refresh = false
            error = false
        }
        if (error) {
            it.loadUrl("about:blank")
        }
    }, modifier = modifier.fillMaxSize()
    )

    if (isLoading) {
        LoadingData(modifier)
    }
    if (error) {
        NotInternetConnection(modifier, refresh = { refresh = true })
    }
}




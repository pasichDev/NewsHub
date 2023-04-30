package com.pasichdev.newshub.fragment.viewNewsFragment

import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.pasichdev.newshub.viewmodel.ExploreViewModel
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@Composable
fun ViewNewsFragment(
    newsUrl: String, modifier: Modifier, exploreViewModel: ExploreViewModel = hiltViewModel()
) {
    val urlNews = URLDecoder.decode(
        newsUrl, StandardCharsets.UTF_8.toString()
    )
    AndroidView(factory = {
        WebView(it).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
            )
            webViewClient = WebViewClient()
            loadUrl(urlNews)
        }
    }, update = {
        it.loadUrl(urlNews)
    })
}


@Composable
fun ScreenTest(stingTest: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = stingTest,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )
    }

}

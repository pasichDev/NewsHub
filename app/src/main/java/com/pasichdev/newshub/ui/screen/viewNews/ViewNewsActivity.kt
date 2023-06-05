package com.pasichdev.newshub.ui.screen.viewNews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.pasichdev.newshub.ui.screen.viewNews.screen.WebViewWithNews
import com.pasichdev.newshub.ui.theme.AppTheme
import com.pasichdev.newshub.utils.DETAIL_ARG_NEWS_URL
import com.pasichdev.newshub.utils.DETAIL_ARG_SAVED_STATUS
import dagger.hilt.android.AndroidEntryPoint
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@AndroidEntryPoint
class ViewNewsActivity : ComponentActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                ViewNewsScreen()


            }
        }


    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ViewNewsScreen(modifier: Modifier = Modifier) {
        val urlNews = URLDecoder.decode(
            intent.getStringExtra(DETAIL_ARG_NEWS_URL), StandardCharsets.UTF_8.toString()
        )
        val savedNews = intent.getBooleanExtra(DETAIL_ARG_SAVED_STATUS, false)


        Scaffold(topBar = {
            TopAppBar(title = {

            }, navigationIcon = {
                IconButton(onClick = {
                    finish()
                }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack, contentDescription = "Go Back"
                    )
                }
            }, actions = {
                IconButton(onClick = {
                }) {
                    Icon(imageVector = Icons.Outlined.Share, contentDescription = "Share")
                }
                IconButton(onClick = {

                }) {
                    Icon(
                        imageVector = Icons.Outlined.Favorite,
                        tint = if (savedNews) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline,
                        contentDescription = "Add Save"
                    )
                }
            })
        }, content = { padding ->
            WebViewWithNews(
                newsUrl = urlNews, modifier = modifier.padding(padding)
            )
        })

    }
}


@Preview(showBackground = true)
@Composable
fun ViewNewsActivityPreview() {
    AppTheme {

    }
}
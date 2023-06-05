package com.pasichdev.newshub.ui.screen.viewNews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pasichdev.newshub.ui.components.bottombarviewactivity.ClickListenerAppBar
import com.pasichdev.newshub.ui.components.moreDialog.BottomSheetContent
import com.pasichdev.newshub.ui.screen.viewNews.screen.WebViewWithNews
import com.pasichdev.newshub.ui.theme.AppTheme
import com.pasichdev.newshub.utils.DETAIL_ARG_NEWS_URL
import com.pasichdev.newshub.utils.DETAIL_ARG_SAVED_STATUS
import com.pasichdev.newshub.utils.openViewNewsActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@AndroidEntryPoint
class ViewNewsActivity : ComponentActivity() {


    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val urlNews = URLDecoder.decode(
                intent.getStringExtra(DETAIL_ARG_NEWS_URL), StandardCharsets.UTF_8.toString()
            )
            val savedNews = intent.getBooleanExtra(DETAIL_ARG_SAVED_STATUS, false)
            val context = LocalContext.current
            val coroutineScope = rememberCoroutineScope()
            val scaffoldState = rememberBottomSheetScaffoldState()


            AppTheme(colorNavigationDefault = true) {

                BottomSheetScaffold(
                    scaffoldState = scaffoldState,
                    sheetPeekHeight = 72.dp,
                    sheetContent = {
                        BottomSheetContent(clickListenerAppBar = object : ClickListenerAppBar {
                            override fun savedNews() {
                                TODO("Not yet implemented")
                            }

                            override fun shareNews() {
                                openViewNewsActivity(context, urlNews, savedNews)
                            }

                            override fun moreNews() {
                                coroutineScope.launch {
                                    if (!scaffoldState.bottomSheetState.isExpanded)
                                        scaffoldState.bottomSheetState.expand()
                                    else
                                        scaffoldState.bottomSheetState.collapse()
                                }
                            }

                            override fun openNewsOtherAuthor() {
                            }
                        })

                    }

                ) {

                    ViewNewsScreen(urlNews = urlNews)
                }


            }


        }


    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ViewNewsScreen(
        modifier: Modifier = Modifier,
        urlNews: String
    ) {
        Scaffold(topBar = {
            TopAppBar(title = {}, navigationIcon = {
                IconButton(onClick = { finish() }) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Go Back")
                }
            }, actions = {

            })
        }, content = { padding ->
            WebViewWithNews(
                newsUrl = urlNews, modifier = modifier.padding(padding)
            )
        }, bottomBar = {

        })

    }
}


@Preview(showBackground = true)
@Composable
fun ViewNewsActivityPreview() {
    AppTheme {}
}
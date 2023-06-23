package com.pasichdev.newshub.ui.screen.viewNews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState
import com.pasichdev.newshub.data.model.News
import com.pasichdev.newshub.ui.components.bottombarviewactivity.ClickListenerAppBar
import com.pasichdev.newshub.ui.components.moreDialog.BottomSheetContent
import com.pasichdev.newshub.ui.theme.AppTheme
import com.pasichdev.newshub.utils.DETAIL_ARG_NEWS
import com.pasichdev.newshub.utils.DETAIL_ARG_SAVED_STATUS
import com.pasichdev.newshub.utils.shareNews
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ViewNewsActivity : ComponentActivity() {


    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            val viewModel: ViewNewsViewModel = hiltViewModel()
            val news: News? = intent.getParcelableExtra(DETAIL_ARG_NEWS)
            val savedNews = intent.getBooleanExtra(DETAIL_ARG_SAVED_STATUS, false)
            val context = LocalContext.current
            val uriHandler = LocalUriHandler.current
            val coroutineScope = rememberCoroutineScope()
            val scaffoldState = rememberBottomSheetScaffoldState()
            val isElevationNewsBox = remember { mutableStateOf(false) }



            AppTheme(colorNavigationDefault = true) {
                LaunchedEffect(scaffoldState) {
                    snapshotFlow { scaffoldState.bottomSheetState.currentValue }.collect {
                        when (it) {
                            BottomSheetValue.Collapsed -> {
                                isElevationNewsBox.value = false
                            }

                            BottomSheetValue.Expanded -> {
                                isElevationNewsBox.value = true
                            }
                        }
                    }
                }

                BottomSheetScaffold(
                    scaffoldState = scaffoldState,
                    sheetPeekHeight = 72.dp,
                    sheetShape = RoundedCornerShape(20.dp),
                    sheetContent = {
                        BottomSheetContent(
                            savedNews = savedNews,
                            clickListenerAppBar = object : ClickListenerAppBar {
                                override fun saved() {
                                    if (news != null) {
                                        viewModel.savedNews(news, savedNews)
                                    }
                                }

                                override fun share() {
                                    if (news != null) {
                                        shareNews(context, news.url)
                                    }
                                }

                                override fun more() {
                                coroutineScope.launch {
                                    if (!scaffoldState.bottomSheetState.isExpanded) {
                                        scaffoldState.bottomSheetState.expand()

                                    } else {
                                        scaffoldState.bottomSheetState.collapse()
                                    }

                                }
                                }

                                override fun openNewsOtherAuthor() {

                                }

                                override fun openBrowser() {
                                    if (news != null) {
                                        uriHandler.openUri(news.url)
                                    }
                                }
                            })

                    }

                ) {

                    if (news != null) {
                        ViewNewsScreen(urlNews = news.url, isElevationNewsBox = isElevationNewsBox)
                    }
                }


            }


        }


    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ViewNewsScreen(
        modifier: Modifier = Modifier,
        urlNews: String,
        isElevationNewsBox: MutableState<Boolean>
    ) {
        Scaffold(topBar = {
            TopAppBar(title = {}, navigationIcon = {
                if (!isElevationNewsBox.value)
                    IconButton(onClick = { finish() }) {
                        Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "Go Back")
                    }
            })
        }) { padding ->
            val clip = if (isElevationNewsBox.value) {
                10.dp
            } else {
                0.dp
            }


            val scale by animateFloatAsState(
                targetValue = if (isElevationNewsBox.value) 0.9f else 1.0f,
                animationSpec = TweenSpec(durationMillis = 100)
            )


            val cardShape = RoundedCornerShape(clip)

            val webViewState = rememberWebViewState(urlNews)
            Card(
                modifier = modifier
                    .padding(paddingValues = padding)
                    .scale(scale)
                    .shadow(shape = cardShape, elevation = 12.dp),
                shape = cardShape
            ) {
                WebView(
                    modifier = modifier,
                    state = webViewState,
                    onCreated = { it.settings.javaScriptEnabled = true }
                )
            }


        }
    }
}


@Preview(showBackground = true)
@Composable
fun ViewNewsActivityPreview() {
    AppTheme {}
}
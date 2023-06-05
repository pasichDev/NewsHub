package com.pasichdev.newshub.ui.screen.viewNews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
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
            val isElevationNewsBox = remember { mutableStateOf(false) }


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
                                isElevationNewsBox.value = !isElevationNewsBox.value

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
                        })

                    }

                ) {

                    ViewNewsScreen(urlNews = urlNews, isElevationNewsBox = isElevationNewsBox)
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
        }, content = { padding ->
            val clip = if (isElevationNewsBox.value) {
                10.dp
            } else {
                0.dp
            }


            val scale by animateFloatAsState(
                targetValue = if (isElevationNewsBox.value) 0.9f else 1.0f,
                animationSpec = TweenSpec(durationMillis = 300)
            )



            WebViewWithNews(
                newsUrl = urlNews,
                modifier = modifier
                    .padding(paddingValues = padding)
                    .scale(scale)
                    .clip(
                        RoundedCornerShape(clip)
                    )
            )
        })

    }
}


@Preview(showBackground = true)
@Composable
fun ViewNewsActivityPreview() {
    AppTheme {}
}
package com.pasichdev.newshub.ui.screen.viewNews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pasichdev.newshub.R
import com.pasichdev.newshub.ui.components.moreDialog.BottomSheetContent
import com.pasichdev.newshub.ui.screen.viewNews.screen.WebViewWithNews
import com.pasichdev.newshub.ui.theme.AppTheme
import com.pasichdev.newshub.utils.DETAIL_ARG_NEWS_URL
import com.pasichdev.newshub.utils.DETAIL_ARG_SAVED_STATUS
import com.pasichdev.newshub.utils.shareNews
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@AndroidEntryPoint
class ViewNewsActivity : ComponentActivity() {


    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val modalBottomSheetState =
                rememberModalBottomSheetState(ModalBottomSheetValue.Expanded)
            val scope = rememberCoroutineScope()

            AppTheme(colorNavigationDefault = true) {

                ModalBottomSheetLayout(
                    sheetContent = {
                        BottomSheetContent()
                    },
                    sheetState = modalBottomSheetState,
                    sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
                    sheetBackgroundColor = MaterialTheme.colorScheme.surface,

                    ) {
                    ViewNewsScreen(scope = scope, state = modalBottomSheetState)
                }

            }
        }


    }

    @OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
    @Composable
    fun ViewNewsScreen(
        modifier: Modifier = Modifier,
        scope: CoroutineScope,
        state: ModalBottomSheetState
    ) {
        val urlNews = URLDecoder.decode(
            intent.getStringExtra(DETAIL_ARG_NEWS_URL), StandardCharsets.UTF_8.toString()
        )
        val savedNews = intent.getBooleanExtra(DETAIL_ARG_SAVED_STATUS, false)
        val context = LocalContext.current


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
            BottomAppBar(modifier = modifier.height(72.dp),
                contentColor = MaterialTheme.colorScheme.surfaceVariant,
                actions = {
                    OutlinedButton(
                        onClick = { /*TODO*/ },
                        modifier = modifier.padding(start = 15.dp),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colorScheme.outline)

                    ) {
                        Text(text = stringResource(id = R.string.titleSearchNews))

                    }
                }, floatingActionButton = {
                    Row(
                        modifier = modifier.padding(end = 10.dp)
                    ) {
                        IconButton(onClick = { shareNews(context, urlNews) }) {
                            Icon(
                                imageVector = Icons.Outlined.Share,
                                contentDescription = "Share",
                                tint = MaterialTheme.colorScheme.outline
                            )
                        }
                        IconButton(onClick = {}) {
                            Icon(
                                imageVector = Icons.Outlined.Favorite,
                                tint = if (savedNews) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline,
                                contentDescription = "Add Save"
                            )
                        }
                        IconButton(onClick = {
                            scope.launch {
                                state.show()
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Outlined.MoreVert,
                                tint = MaterialTheme.colorScheme.outline,
                                contentDescription = "More"
                            )
                        }
                    }
                }

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
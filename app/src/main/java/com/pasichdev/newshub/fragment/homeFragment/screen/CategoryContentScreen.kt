package com.pasichdev.newshub.fragment.homeFragment.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.pasichdev.newshub.data.model.News
import com.pasichdev.newshub.ui.components.ItemHeadLineNews
import com.pasichdev.newshub.ui.components.LoadingData
import com.pasichdev.newshub.ui.components.NotInternetConnection
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CategoryContent(
    modifier: Modifier,
    newsList: LazyPagingItems<News>? = null,
    savedNewsList: List<News>,
    onClick: (News) -> Unit = {},
    savedClick: (News, Boolean) -> Unit = { _: News, _: Boolean -> }
) {
    val refreshScope = rememberCoroutineScope()
    var refreshing by remember { mutableStateOf(false) }
    var itemCount by remember { mutableStateOf(15) }

    fun refresh() = refreshScope.launch {
        refreshing = true
        delay(1500)
        itemCount += 5
        refreshing = false
    }

    val state = rememberPullRefreshState(refreshing, ::refresh)

    if (newsList == null) return

    Box(Modifier.pullRefresh(state)) {


        LazyColumn(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            if (!refreshing) {
                items(newsList.itemCount) { index ->

                    newsList[index]?.let { news ->

                        var isSaved = false
                        if (savedNewsList.find { sn -> sn.url == news.url } != null) {
                            isSaved = true

                        }

                        ItemHeadLineNews(news,
                            modifier = modifier,
                            savedNews = isSaved,
                            savedClick = { saved -> savedClick.invoke(news, saved) },
                            onClick = { onClick.invoke(news) })
                    }

                }
            }
            newsList.apply {
                item {
                    when {
                        loadState.refresh is LoadState.Loading || loadState.append is LoadState.Loading -> {
                            LoadingData()
                        }

                        loadState.refresh is LoadState.Error || loadState.append is LoadState.Error -> {
                            NotInternetConnection(modifier, refresh = {
                                retry()
                            })

                        }
                    }
                }
            }


        }

        //standard Pull-Refresh indicator. You can also use a custom indicator
        PullRefreshIndicator(refreshing, state, Modifier.align(Alignment.TopCenter))
    }


}

package com.pasichdev.newshub.ui.screen.home.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.pasichdev.newshub.ui.components.ItemHeadLineNews
import com.pasichdev.newshub.ui.components.message.LoadingData
import com.pasichdev.newshub.ui.components.message.NotInternetConnection
import com.pasichdev.newshub.ui.screen.ClickNews
import com.pasichdev.newshub.ui.screen.home.HomeState

@Composable
fun NewsList(
    modifier: Modifier,
    state: HomeState = HomeState(),
    clickNews: ClickNews
) {


    val savedNews = state.savedNews
    val news = state.news?.collectAsLazyPagingItems()



    Box(modifier) {


        LazyColumn(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            items(news?.itemCount ?: 0) { index ->

                news?.get(index)?.let {

                    var isSaved = false
                    if (savedNews?.find { sn -> sn.url == it.url } != null) {
                        isSaved = true

                    }

                    ItemHeadLineNews(it,
                        modifier = modifier,
                        savedNews = isSaved,
                        clickNews = clickNews
                    )

                }

            }
            news.apply {
                item {
                    when (news?.loadState?.refresh) {
                        is LoadState.Loading -> LoadingData()
                        is LoadState.Error -> NotInternetConnection(
                            modifier,
                            refresh = { news.retry() })

                        else -> {}
                    }
                }
            }


        }

    }


}

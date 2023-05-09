package com.pasichdev.newshub.fragment.homeFragment.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.pasichdev.newshub.data.model.News
import com.pasichdev.newshub.ui.components.ItemHeadLineNews
import com.pasichdev.newshub.ui.components.LoadingData
import com.pasichdev.newshub.ui.components.NotInternetConnection

@Composable
fun CategoryContent(
    modifier: Modifier,
    newsList: LazyPagingItems<News>? = null,
    savedNewsList: List<News>,
    onClick: (News) -> Unit = {},
    savedClick: (News, Boolean) -> Unit = { _: News, _: Boolean -> }
) {

    if (newsList == null) return
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {


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


}

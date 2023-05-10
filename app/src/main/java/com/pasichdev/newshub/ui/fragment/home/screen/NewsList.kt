package com.pasichdev.newshub.ui.fragment.home.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.pasichdev.newshub.data.model.News
import com.pasichdev.newshub.ui.components.ItemHeadLineNews
import com.pasichdev.newshub.ui.components.LoadingData
import com.pasichdev.newshub.ui.components.NotInternetConnection
import com.pasichdev.newshub.ui.fragment.home.HomeViewModel

@Composable
fun NewsList(
    modifier: Modifier,
    homeViewModel: HomeViewModel,
    nameCategory: String,
    onClick: (News) -> Unit = {},
) {


    val state = homeViewModel.state.collectAsStateWithLifecycle()
    val savedNews = state.value.savedNews
    val news = state.value.news?.collectAsLazyPagingItems()



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
                        savedClick = { saved -> homeViewModel.savedNews(it, saved) },
                        onClick = { onClick.invoke(it) })

                }

            }
            news.apply {
                item {
                    when (news?.loadState?.refresh) {
                        is LoadState.Loading -> {
                            LoadingData()
                        }

                        is LoadState.Error -> {
                            NotInternetConnection(modifier, refresh = {
                                news.retry()
                            })

                        }

                        else -> {}
                    }
                }
            }


        }

    }


}

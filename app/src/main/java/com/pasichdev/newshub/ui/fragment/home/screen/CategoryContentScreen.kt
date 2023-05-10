package com.pasichdev.newshub.ui.fragment.home.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.pasichdev.newshub.utils.CountryHeadLines
import java.util.Locale

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CategoryContent(
    modifier: Modifier,
    homeViewModel: HomeViewModel,
    nameCategory: String,
    onClick: (News) -> Unit = {},
) {
    val news by homeViewModel.newsState.collectAsState()
    val savedNews by homeViewModel.savedNews.collectAsState()

    val newsList = homeViewModel.loadCategoryNews(
        category = nameCategory, country = CountryHeadLines.getCountryHeadLines(
            Locale.getDefault().country
        )
    ).collectAsLazyPagingItems()


    val refreshing by homeViewModel.isRefreshing.collectAsStateWithLifecycle()
    val pullRefreshState = rememberPullRefreshState(refreshing, { newsList.refresh() })


    Box(Modifier.pullRefresh(pullRefreshState)) {


        LazyColumn(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            items(newsList.itemCount) { index ->

                newsList[index]?.let { news ->

                    var isSaved = false
                    if (savedNews.find { sn -> sn.url == news.url } != null) {
                        isSaved = true

                    }

                    ItemHeadLineNews(news,
                        modifier = modifier,
                        savedNews = isSaved,
                        savedClick = { saved -> homeViewModel.savedNews(news, saved) },
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

        PullRefreshIndicator(refreshing, pullRefreshState, Modifier.align(Alignment.TopCenter))
    }


}

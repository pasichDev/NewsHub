package com.pasichdev.newshub.fragment.homeFragment.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
    modifier: Modifier, newsList: LazyPagingItems<News>? = null, onClick: (News) -> Unit = {}
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (newsList == null) return
        LazyColumn {

            items(newsList.itemCount) { index ->

                newsList[index]?.let { news ->
                    ItemHeadLineNews(news, Modifier) {
                        onClick.invoke(news)
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
                            NotInternetConnection()

                        }
                    }
                }
            }


        }

    }

}

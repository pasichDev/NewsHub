package com.pasichdev.newshub.ui.screen.saved

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pasichdev.newshub.data.model.News
import com.pasichdev.newshub.ui.components.ItemHeadLineNews
import com.pasichdev.newshub.ui.components.message.SavedNewsEmpty

@Composable
fun SavedFragment(
    modifier: Modifier,
    savedViewModel: SavedViewModel = hiltViewModel(),
    onClick: (News) -> Unit = {}
) {
    val state = savedViewModel.state.collectAsStateWithLifecycle()
    val savedNews = state.value.savedNews
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)
            .padding(top = 36.dp, bottom = 50.dp)
    ) {
        if (savedNews?.size == 0) {
            SavedNewsEmpty(modifier)
        } else {
            LazyColumn(
                modifier = modifier.fillMaxSize()
            ) {


                items(savedNews?.size ?: 0) {
                    savedNews?.get(it)?.let { news ->
                        ItemHeadLineNews(
                            news = news,
                            modifier = modifier,
                            onClick = { onClick.invoke(news) },
                            savedClick = { savedViewModel.deleteSavedNews(news) },
                            savedFragment = true
                        )
                    }

                }

            }
        }
    }


}





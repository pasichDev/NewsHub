package com.pasichdev.newshub.ui.fragment.saved

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pasichdev.newshub.data.model.News
import com.pasichdev.newshub.ui.components.ItemHeadLineNews

@Composable
fun SavedFragment(
    modifier: Modifier,
    savedViewModel: SavedViewModel = hiltViewModel(),
    onClick: (News) -> Unit = {}
) {
    val savedNews by savedViewModel.savedNews.collectAsState()
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(20.dp)
            .padding(top = 36.dp, bottom = 50.dp)
    ) {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {


            items(savedNews) { item: News ->
                ItemHeadLineNews(
                    news = item,
                    onClick = { onClick.invoke(item) },
                    savedNews = true, modifier = modifier
                )

            }

        }
    }


}



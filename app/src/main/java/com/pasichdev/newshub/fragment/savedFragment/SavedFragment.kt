package com.pasichdev.newshub.fragment.savedFragment

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pasichdev.newshub.data.model.News
import com.pasichdev.newshub.ui.components.ItemHeadLineNews
import com.pasichdev.newshub.viewmodel.SavedViewModel

@Composable
fun SavedFragment(
    modifier: Modifier,
    savedViewModel: SavedViewModel = hiltViewModel()
) {
    val savedList by savedViewModel.allSavedNews.observeAsState(listOf())
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


            items(savedList) { item: News ->
                ItemHeadLineNews(
                    news = item,
                    savedNews = true, modifier = modifier
                )

            }

        }
    }
}



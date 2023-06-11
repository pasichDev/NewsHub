package com.pasichdev.newshub.ui.screen.home

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pasichdev.newshub.data.model.News
import com.pasichdev.newshub.ui.screen.ClickNews
import com.pasichdev.newshub.ui.screen.home.screen.NewsList
import com.pasichdev.newshub.ui.theme.itimFontFamily
import com.pasichdev.newshub.utils.openViewNewsActivity


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier,
    homeViewModel: HomeViewModel = hiltViewModel(),
    context: Context = LocalContext.current,
    clickNews: ClickNews = object : ClickNews {
        override fun clickNews(news: News, saved: Boolean) {
            openViewNewsActivity(context, news, saved)
        }

        override fun savedNews(news: News, saved: Boolean) {
            homeViewModel.savedNews(news, saved)
        }
    }
) {
    val state by homeViewModel.state.collectAsStateWithLifecycle()


    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(20.dp)
            .padding(top = 36.dp, bottom = 50.dp)
    ) {
        ScrollableTabRow(selectedTabIndex = state.tabIndex,
            edgePadding = 0.dp,
            indicator = {},
            divider = {}) {
            state.categoryNews.forEachIndexed { index, nameCategory ->

                FilterChip(
                    modifier = modifier
                        .padding(end = 10.dp)
                        .padding(vertical = 10.dp),

                    selected = state.tabIndex == index,
                    onClick = {
                        state.tabIndex = index
                        homeViewModel.onCategoryChanged(index)
                    },
                    label = {
                        Text(
                            text = stringResource(id = nameCategory),
                            fontFamily = itimFontFamily,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center,
                            modifier = modifier.padding(8.dp),
                            color = if (state.tabIndex == index) MaterialTheme.colorScheme.onSurfaceVariant else MaterialTheme.colorScheme.onBackground,
                            fontWeight = if (state.tabIndex == index) FontWeight.Bold else FontWeight.Normal
                        )
                    },
                    colors = FilterChipDefaults.filterChipColors(selectedContainerColor = MaterialTheme.colorScheme.surfaceVariant),
                    border = FilterChipDefaults.filterChipBorder(
                        borderColor = if (state.tabIndex == index) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant,
                    ),
                    shape = RoundedCornerShape(10.dp),
                    trailingIcon = {},
                    leadingIcon = {}

                )


            }
        }


        NewsList(
            modifier, state, clickNews = clickNews
        )

    }
}



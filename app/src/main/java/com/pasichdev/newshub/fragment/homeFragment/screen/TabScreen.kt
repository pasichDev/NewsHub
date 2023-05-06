package com.pasichdev.newshub.fragment.homeFragment.screen

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
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.collectAsLazyPagingItems
import com.pasichdev.newshub.data.model.News
import com.pasichdev.newshub.ui.theme.itimFontFamily
import com.pasichdev.newshub.utils.CountryHeadLines
import com.pasichdev.newshub.viewmodel.HomeViewModel
import hilt_aggregated_deps.data.NewsCategory.tagsNewsIndex
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TabScreen(viewModel: HomeViewModel, modifier: Modifier, onClick: (News) -> Unit = {}) {
    var tabIndex by remember { viewModel.categoryNewsIndex }
    val tabs = viewModel.categoryNews

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        ScrollableTabRow(
            selectedTabIndex = tabIndex,
            edgePadding = 0.dp,
            indicator = {},
            divider = {}) {
            tabs.forEachIndexed { index, nameCategory ->

                FilterChip(
                    modifier = modifier
                        .padding(end = 10.dp)
                        .padding(vertical = 10.dp),

                    selected = tabIndex == index,
                    onClick = { tabIndex = index },
                    label = {
                        Text(
                            text = stringResource(id = nameCategory),
                            fontFamily = itimFontFamily,
                            fontWeight = FontWeight.Normal,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center,
                            modifier = modifier.padding(8.dp)
                        )
                    },
                    colors = FilterChipDefaults.filterChipColors(selectedContainerColor = MaterialTheme.colorScheme.surfaceVariant),
                    border = FilterChipDefaults.filterChipBorder(
                        borderColor = if (tabIndex == index) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant,
                    ),
                    shape = RoundedCornerShape(10.dp),
                    trailingIcon = {},
                    leadingIcon = { }

                )


            }
        }
        CategoryContent(
            modifier = modifier,
            newsList = viewModel.loadCategoryNews(
                category = tagsNewsIndex[tabIndex],
                country = CountryHeadLines.getCountryHeadLines(Locale.getDefault().country)
            )
                .collectAsLazyPagingItems(),
            onClick = onClick
        )

    }
}


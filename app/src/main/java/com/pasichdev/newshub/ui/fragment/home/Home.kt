package com.pasichdev.newshub.ui.fragment.home

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
import androidx.hilt.navigation.compose.hiltViewModel
import com.pasichdev.newshub.data.model.News
import com.pasichdev.newshub.ui.fragment.home.screen.NewsList
import com.pasichdev.newshub.ui.theme.itimFontFamily


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeFragment(
    modifier: Modifier,
    homeViewModel: HomeViewModel = hiltViewModel(),
    onClick: (News) -> Unit = {},
) {


    var tabIndex by remember { homeViewModel.state.value.categoryNewsIndex }
    val tabs = homeViewModel.categoryNews
    val tabsIndex = homeViewModel.tagsNewsIndex


    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(20.dp)
            .padding(top = 36.dp, bottom = 50.dp)
    ) {
        ScrollableTabRow(
            selectedTabIndex = tabIndex,
            edgePadding = 0.dp,
            indicator = {},
            divider = {}) {
            tabs.forEachIndexed { index, nameCategory ->

                FilterChip(modifier = modifier
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
                    leadingIcon = {}

                )


            }
        }


        NewsList(
            modifier,
            homeViewModel,
            tabsIndex[tabIndex],
            onClick
        )

    }
}


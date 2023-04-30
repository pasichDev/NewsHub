package com.pasichdev.newshub.fragment.homeFragment

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pasichdev.newshub.R
import com.pasichdev.newshub.fragment.homeFragment.screen.ListNews
import com.pasichdev.newshub.ui.components.SubTitleFragment
import com.pasichdev.newshub.ui.theme.itimFontFamily
import com.pasichdev.newshub.utils.Response
import com.pasichdev.newshub.viewmodel.HomeViewModel

@Composable
fun HomeFragment(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(20.dp)
            .padding(top = 56.dp, bottom = 50.dp)
    ) {
        SubTitleFragment(modifier = modifier)
        TabScreen(viewModel = homeViewModel)

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TabScreen(viewModel: HomeViewModel) {
    var tabIndex by remember { viewModel.categoryNewsIndex }
    val tabs = viewModel.categoryNews

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp)
    ) {
        ScrollableTabRow(
            selectedTabIndex = tabIndex,
            edgePadding = 0.dp,
            indicator = {},
            divider = {}) {
            tabs.forEachIndexed { index, title ->

                FilterChip(modifier = Modifier
                    .padding(end = 10.dp)
                    .padding(vertical = 10.dp),

                    selected = tabIndex == index,
                    onClick = { tabIndex = index },
                    label = {
                        Text(
                            text = title,
                            fontFamily = itimFontFamily,
                            fontWeight = FontWeight.Normal,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(8.dp)
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
        TabContentScreen(data = tabs[tabIndex])

    }
}


@Composable
fun TabContentScreen(data: String) {
    Column(
        modifier = Modifier.fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = data,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}


@Composable
fun NewsCategoryScreen(nameCategory: String, homeViewModel: HomeViewModel) {


    when (val gamesResponse = homeViewModel.newsState.value) {
        is Response.Loading -> {
            Text(text = "LoadinggList")
        }

        is Response.Success -> {

            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = nameCategory,
                        fontFamily = itimFontFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = 24.sp,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    TextButton(onClick = { /*TODO*/ }) {
                        Text(
                            text = stringResource(id = R.string.moreNews),
                            fontFamily = itimFontFamily,
                            fontWeight = FontWeight.Normal,
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }


                }
                ListNews(modifier = Modifier, courseList = gamesResponse.data?.results)
            }
        }

        is Response.Failure -> {
            Text(text = "Error list")
        }


    }


}
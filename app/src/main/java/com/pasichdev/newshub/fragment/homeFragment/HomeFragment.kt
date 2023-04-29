package com.pasichdev.newshub.fragment.homeFragment

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pasichdev.newshub.R
import com.pasichdev.newshub.fragment.homeFragment.screen.ListNews
import com.pasichdev.newshub.ui.theme.itimFontFamily
import com.pasichdev.newshub.utils.Response
import com.pasichdev.newshub.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    fun launch() {
        homeViewModel.getNewsCategory("Apple")
    }

    launch()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(20.dp)
            .padding(top = 56.dp, bottom = 50.dp)
            .verticalScroll(rememberScrollState())
    ) {
        NewsCategoryScreen(nameCategory = "Apple", homeViewModel)

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
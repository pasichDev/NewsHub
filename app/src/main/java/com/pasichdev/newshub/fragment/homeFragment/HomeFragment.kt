package com.pasichdev.newshub.fragment.homeFragment

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pasichdev.newshub.fragment.homeFragment.screen.TabScreen
import com.pasichdev.newshub.ui.theme.AppTheme
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
            .padding(top = 36.dp, bottom = 50.dp)
    ) {
        TabScreen(viewModel = homeViewModel, modifier = modifier)

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppTheme {
        HomeFragment()
    }
}
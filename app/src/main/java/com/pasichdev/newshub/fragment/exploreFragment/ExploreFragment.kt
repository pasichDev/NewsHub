package com.pasichdev.newshub.fragment.exploreFragment

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.pasichdev.newshub.fragment.homeFragment.ScreenTest
import com.pasichdev.newshub.viewmodel.ExploreViewModel

@Composable
fun ExploreFragment(
    modifier: Modifier,
    exploreViewModel: ExploreViewModel = hiltViewModel()
) {
    ScreenTest("Explore")
}



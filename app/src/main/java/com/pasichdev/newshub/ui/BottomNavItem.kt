package com.pasichdev.newshub.ui

import com.pasichdev.newshub.R
import com.pasichdev.newshub.utils.EXPLORE_SCREEN
import com.pasichdev.newshub.utils.HOME_SCREEN
import com.pasichdev.newshub.utils.SAVED_SCREEN

sealed class BottomNavItem(var title: Int, var icon: Int, var screen_route: String) {

    object Home : BottomNavItem(R.string.homeNav, R.drawable.ic_home_newspaper, HOME_SCREEN)
    object Explore : BottomNavItem(R.string.exploreNav, R.drawable.ic_explore, EXPLORE_SCREEN)
    object Saved : BottomNavItem(R.string.savedNav, R.drawable.ic_saved, SAVED_SCREEN)
}
package com.pasichdev.newshub.data.data

import com.pasichdev.newshub.R

sealed class BottomNavItem(var title: String, var icon: Int, var screen_route: String) {

    object Home : BottomNavItem("Home", R.drawable.ic_home, "Home")
    object Explore : BottomNavItem("Explore", R.drawable.ic_explore, "Explore")
    object Saved : BottomNavItem("Saved", R.drawable.ic_saved, "Saved")
    object Settings : BottomNavItem("Settings", R.drawable.ic_settings, "Settings")
}
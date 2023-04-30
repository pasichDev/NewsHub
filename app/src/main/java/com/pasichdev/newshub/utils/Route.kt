package com.pasichdev.newshub.utils

sealed class Route(val route: String) {
    //  object Home: Route(BottomNavItem.Home.screen_route)
    object Detail : Route("$DETAIL_SCREEN/{$DETAIL_ARG_NEWS_ID}") {
        fun createRoute(newsUrl: String) = "$DETAIL_SCREEN/$newsUrl"
    }
}
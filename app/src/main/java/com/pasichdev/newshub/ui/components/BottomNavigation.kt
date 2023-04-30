package com.pasichdev.newshub.ui.components

import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.pasichdev.newshub.data.data.BottomNavItem


@Composable
fun BottomNavigation(navController: NavController) {
    val items = listOf(
        BottomNavItem.Home, BottomNavItem.Explore, BottomNavItem.Saved, BottomNavItem.Settings
    )

    androidx.compose.material.BottomNavigation(
        backgroundColor = MaterialTheme.colorScheme.background, contentColor = Color.Black
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            BottomNavigationItem(icon = {
                Icon(
                    painterResource(id = item.icon),
                    contentDescription = item.title,
                    tint = if (currentRoute.equals(item.screen_route)) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground
                )
            },
                label = {
                    Text(
                        text = item.title,
                        fontSize = 12.sp,
                        color = if (currentRoute.equals(item.screen_route)) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onBackground
                    )
                },
                selectedContentColor = MaterialTheme.colorScheme.primary,
                unselectedContentColor = Color.Black.copy(0.4f),
                alwaysShowLabel = true,
                selected = currentRoute == item.screen_route,
                onClick = {
                    navController.navigate(item.screen_route) {

                        navController.graph.startDestinationRoute?.let { screen_route ->
                            popUpTo(screen_route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                })
        }
    }
}


package com.pasichdev.newshub

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.pasichdev.newshub.data.BottomNavItem
import com.pasichdev.newshub.ui.theme.AppTheme
import com.pasichdev.newshub.ui.theme.titleBoldFont
import com.pasichdev.newshub.ui.view.HomeScreen


class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                MainScreen()
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row() {
                        Text(
                            "News ",
                            color = MaterialTheme.colorScheme.onBackground,
                            style = TextStyle(
                                fontFamily = titleBoldFont,
                                fontWeight = FontWeight.Bold,
                                fontSize = 28.sp,
                                lineHeight = 28.sp,
                                letterSpacing = 0.sp
                            )
                        )
                        Text(
                            "Hub", color = MaterialTheme.colorScheme.primary, style = TextStyle(
                                fontFamily = titleBoldFont,
                                fontWeight = FontWeight.Bold,
                                fontSize = 28.sp,
                                lineHeight = 28.sp,
                                letterSpacing = 0.sp
                            )
                        )

                    }


                },
                Modifier.background(MaterialTheme.colorScheme.background)
            )
        },
        content = { NavigationGraph(navController = navController) },
        bottomBar = { BottomNavigation(navController = navController) }
    )
}


@Composable
fun BottomNavigation(navController: NavController) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Explore,
        BottomNavItem.Saved,
        BottomNavItem.Settings
    )

    BottomNavigation(
        backgroundColor = MaterialTheme.colorScheme.background,
        contentColor = Color.Black
    )
    {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painterResource(id = item.icon), contentDescription = item.title,
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
                }
            )
        }
    }
}


@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController, startDestination = BottomNavItem.Home.screen_route) {
        composable(BottomNavItem.Home.screen_route) {
            HomeScreen()
        }
        composable(BottomNavItem.Explore.screen_route) {
            ScreenTest("Explore")
        }
        composable(BottomNavItem.Saved.screen_route) {
            ScreenTest("Saved")
        }
        composable(BottomNavItem.Settings.screen_route) {
            ScreenTest("Settings")
        }

    }
}


@Composable
fun ScreenTest(stingTest: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white))
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = stingTest,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )
    }

}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppTheme {
        MainScreen()
    }
}
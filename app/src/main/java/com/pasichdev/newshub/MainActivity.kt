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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.pasichdev.newshub.fragment.exploreFragment.ExploreFragment
import com.pasichdev.newshub.fragment.homeFragment.HomeFragment
import com.pasichdev.newshub.fragment.viewNewsFragment.ViewNewsFragment
import com.pasichdev.newshub.ui.components.BottomNavigation
import com.pasichdev.newshub.ui.theme.AppTheme
import com.pasichdev.newshub.ui.theme.itimFontFamily
import com.pasichdev.newshub.utils.DETAIL_ARG_NEWS_ID
import com.pasichdev.newshub.utils.EXPLORE_SCREEN
import com.pasichdev.newshub.utils.HOME_SCREEN
import com.pasichdev.newshub.utils.Route
import com.pasichdev.newshub.utils.SAVED_SCREEN
import com.pasichdev.newshub.utils.SETTINGS_SCREEN
import dagger.hilt.android.AndroidEntryPoint
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                MainScreen()
            }
        }
            //  Log.wtf(TAG, "onCreate: "+   Locale.getDefault().country)
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    Scaffold(topBar = {
        TopAppBar(
            title = {
                // TODO: Упростить этот вариант кода
                Row {
                    Text(
                        "News ",
                        fontFamily = itimFontFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = 28.sp,
                    )
                    Text(
                        "Hub",
                        fontFamily = itimFontFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = 28.sp,
                        color = MaterialTheme.colorScheme.primary

                    )

                }


            }, modifier.background(MaterialTheme.colorScheme.background)
        )
    },
        content = { NavigationGraph(navController = navController, modifier) },
        bottomBar = { BottomNavigation(navController = navController) })
}


@Composable
fun NavigationGraph(navController: NavHostController, modifier: Modifier) {
    NavHost(navController, startDestination = HOME_SCREEN) {
        composable(HOME_SCREEN) {
            HomeFragment(modifier, onClick = { news ->
                navController.navigate(
                    Route.Detail.createRoute(
                        URLEncoder.encode(
                            news.url,
                            StandardCharsets.UTF_8.toString()
                        )
                    )
                )
            })
        }
        composable(EXPLORE_SCREEN) {
            ExploreFragment(modifier)
        }
        composable(SAVED_SCREEN) {
            ScreenTest("Saved")
        }
        composable(SETTINGS_SCREEN) {
            ScreenTest("Settings")
        }

        composable(
            route = Route.Detail.route,
            arguments = listOf(
                navArgument(DETAIL_ARG_NEWS_ID) {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val newsUrls = backStackEntry.arguments?.getString(DETAIL_ARG_NEWS_ID)
            requireNotNull(newsUrls) { "Error url" }
            ViewNewsFragment(newsUrl = newsUrls, modifier = modifier)

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
fun MainActivityPreview() {
    AppTheme {
        MainScreen()
    }
}
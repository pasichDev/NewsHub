package com.pasichdev.newshub

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pasichdev.newshub.data.model.News
import com.pasichdev.newshub.fragment.exploreFragment.ExploreFragment
import com.pasichdev.newshub.fragment.homeFragment.HomeFragment
import com.pasichdev.newshub.ui.components.BottomNavigation
import com.pasichdev.newshub.ui.theme.AppTheme
import com.pasichdev.newshub.ui.theme.itimFontFamily
import com.pasichdev.newshub.utils.DETAIL_ARG_NEWS_ID
import com.pasichdev.newshub.utils.EXPLORE_SCREEN
import com.pasichdev.newshub.utils.HOME_SCREEN
import com.pasichdev.newshub.utils.SAVED_SCREEN
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
            val context = LocalContext.current
            HomeFragment(modifier, onClick = { news ->
                openViewNewsActivity(context, news)
            })


        }
        composable(EXPLORE_SCREEN) {
            ExploreFragment(modifier)
        }
        composable(SAVED_SCREEN) {
            //  ScreenTest("Saved")
        }

    }
}


fun openViewNewsActivity(context: Context, news: News) {

    val intent = Intent(context, ViewNewsActivity::class.java)
    intent.putExtra(
        DETAIL_ARG_NEWS_ID, URLEncoder.encode(
            news.url,
            StandardCharsets.UTF_8.toString()
        )
    )
    context.startActivity(intent)

}


@Preview(showBackground = true)
@Composable
fun MainActivityPreview() {
    AppTheme {
        MainScreen()
    }
}
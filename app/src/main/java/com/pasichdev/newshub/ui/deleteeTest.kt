package com.pasichdev.newshub.fragment.homeFragment

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.pasichdev.newshub.R
import com.pasichdev.newshub.data.model.News
import com.pasichdev.newshub.ui.theme.interFontFamily
import com.pasichdev.newshub.ui.theme.itimFontFamily
import com.pasichdev.newshub.ui.theme.sansationFontFamily
import com.pasichdev.newshub.utils.Response
import com.pasichdev.newshub.utils.convertToNewFormat
import com.pasichdev.newshub.viewmodel.HomeViewModel
import kotlinx.coroutines.launch

@Composable
fun ScreenTest(stingTest: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
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


var paddingDp = 8.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListNews(
    modifier: Modifier,
    courseList: List<News>? = null
) {

    if (courseList != null) {
        HorizontalPager(pageCount = courseList.size) { page ->
            ItemNewsCategory(courseList[page], Modifier)
        }
    }
}


@Composable
fun ItemNewsCategory(newsTest: News, modifier: Modifier) {
    OutlinedCard(
        modifier = modifier
            .padding(end = paddingDp, top = 10.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.surfaceVariant),
    ) {
        Column(
            modifier = modifier.padding(paddingDp)
        ) {

            /*Image(
                painter = painterResource(id = newsTest.image),
                contentDescription = "img",

                modifier = modifier
                    .clip(RoundedCornerShape(10.dp))
                    .fillMaxWidth()
                    .padding(paddingDp),
                alignment = Alignment.Center
            )


             */
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(newsTest.urlToImage)
                    .crossfade(true)
                    .build(),
                //  placeholder = painterResource(R.drawable.ic_demo),
                contentDescription = "test",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .padding(paddingDp)
            )
            Text(
                text = newsTest.source.name,
                modifier = modifier.padding(horizontal = paddingDp, vertical = 12.dp),
                fontFamily = sansationFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = newsTest.title,
                modifier = modifier.padding(horizontal = paddingDp, vertical = 0.dp),
                fontFamily = interFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
            newsTest.description?.let {
                Text(
                    text = it,
                    modifier = modifier.padding(horizontal = paddingDp, vertical = paddingDp),
                    fontFamily = sansationFontFamily,
                    fontWeight = FontWeight.Light,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.padding(horizontal = paddingDp, vertical = 12.dp)
            ) {
                /*     Text(
                         text = "Ukraine",
                         fontFamily = sansationFontFamily,
                         fontWeight = FontWeight.Normal,
                         fontSize = 14.sp,
                         color = MaterialTheme.colorScheme.onBackground
                     )

                 */

                Text(
                    text = convertToNewFormat(newsTest.publishedAt),
                    fontFamily = sansationFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.outline
                )
                Box(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = paddingDp),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    HeartAnimation(modifier)
                }

            }
        }
    }
}


@Composable
fun HeartAnimation(modifier: Modifier) {

    val interactionSource = MutableInteractionSource()

    val coroutineScope = rememberCoroutineScope()

    var enabled by remember {
        mutableStateOf(false)
    }

    val scale = remember {
        Animatable(1f)
    }

    Icon(imageVector = Icons.Outlined.Favorite,
        contentDescription = "Save",
        tint = if (enabled) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline,
        modifier = modifier
            .scale(scale = scale.value)
            .size(size = 24.dp)
            .clickable(
                interactionSource = interactionSource, indication = null
            ) {
                enabled = !enabled
                coroutineScope.launch {
                    scale.animateTo(
                        0.8f,
                        animationSpec = tween(100),
                    )
                    scale.animateTo(
                        1f,
                        animationSpec = tween(100),
                    )
                }
            })
}

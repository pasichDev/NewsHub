package com.pasichdev.newshub.ui.view


import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pasichdev.newshub.R
import com.pasichdev.newshub.data.News
import com.pasichdev.newshub.ui.theme.interFontFamily
import com.pasichdev.newshub.ui.theme.sansationFontFamily
import kotlinx.coroutines.launch


var paddingDp = 8.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ListNews() {
    lateinit var courseList: List<News>
    courseList = ArrayList()

    courseList = courseList + News(
        "На Харківщині колишній нардеп роздавав агітки на підтримку окупантів",
        R.drawable.ic_demo,
        "СБУ викрила колишнього нардепа, який роздавав жителям Харківщини «агітки» на підтримку росії під час окупації регіону."
    )
    courseList = courseList + News("JavaScript", R.drawable.ic_demo, "")
    courseList = courseList + News("Python", R.drawable.ic_demo, "")
    courseList = courseList + News("C++", R.drawable.ic_demo, "")
    courseList = courseList + News("C#", R.drawable.ic_demo, "")
    courseList = courseList + News("Java", R.drawable.ic_demo, "")
    courseList = courseList + News("Node Js", R.drawable.ic_demo, "")

    HorizontalPager(pageCount = courseList.size) { page ->
        ItemNewsCategory(courseList[page], Modifier)
    }
}


@Composable
fun ItemNewsCategory(news: News, modifier: Modifier) {
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

            Image(
                painter = painterResource(id = news.image),
                contentDescription = "img",

                modifier = modifier
                    .clip(RoundedCornerShape(10.dp))
                    .fillMaxWidth()
                    .padding(paddingDp),
                alignment = Alignment.Center
            )


            Text(
                text = "Source",
                modifier = modifier.padding(horizontal = paddingDp, vertical = 12.dp),

                fontFamily = sansationFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.outline
            )
            Text(
                text = news.title,
                modifier = modifier.padding(horizontal = paddingDp, vertical = 0.dp),
                fontFamily = interFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = news.value,
                modifier = modifier.padding(horizontal = paddingDp, vertical = paddingDp),
                fontFamily = sansationFontFamily,
                fontWeight = FontWeight.Light,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.padding(horizontal = paddingDp, vertical = 12.dp)
            ) {
                Text(
                    text = "Ukraine",
                    fontFamily = sansationFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = "3 hours argo",
                    modifier = modifier.padding(horizontal = paddingDp),
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

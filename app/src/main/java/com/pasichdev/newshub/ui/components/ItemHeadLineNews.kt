package com.pasichdev.newshub.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.pasichdev.newshub.data.model.News
import com.pasichdev.newshub.ui.theme.interFontFamily
import com.pasichdev.newshub.ui.theme.sansationFontFamily
import com.pasichdev.newshub.utils.convertToNewFormat


var paddingDp = 8.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemHeadLineNews(
    news: News,
    savedNews: Boolean,
    modifier: Modifier,
    onClick: () -> Unit = {},
    savedClick: (Boolean) -> Unit = {}
) {
    OutlinedCard(
        modifier = modifier
            .padding(end = paddingDp, top = 10.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface,
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.surfaceVariant),
        onClick = onClick
    ) {
        Column(
            modifier = modifier.padding(paddingDp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current).data(news.urlToImage)
                    .crossfade(true).build(),
                contentDescription = "ImageNews",
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .clip(RoundedCornerShape(10.dp))
                    .padding(paddingDp)
                    .fillMaxWidth()
            )
            Text(
                text = news.source.name,
                modifier = modifier.padding(horizontal = paddingDp, vertical = 12.dp),
                fontFamily = sansationFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = news.title,
                modifier = modifier.padding(horizontal = paddingDp, vertical = 0.dp),
                fontFamily = interFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
            news.description?.let {
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
                modifier = modifier.padding(horizontal = paddingDp, vertical = 12.dp)
            ) {

                Text(
                    text = convertToNewFormat(news.publishedAt),
                    fontFamily = sansationFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.outline
                )
                Box(
                    modifier
                        .fillMaxWidth()
                        .padding(horizontal = paddingDp),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    SavedIcon(
                        saved = savedNews, modifier = modifier, savedClick = savedClick
                    )
                }

            }
        }
    }
}

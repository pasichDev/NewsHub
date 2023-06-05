package com.pasichdev.newshub.ui.components.bottombarviewactivity

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pasichdev.newshub.R

@Composable
fun BottomAppBarViewNews(
    modifier: Modifier = Modifier,
    savedNews: Boolean = false,
    clickListenerAppBar: ClickListenerAppBar

) {
    BottomAppBar(modifier = Modifier
        .height(72.dp)
        .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)),
        contentColor = MaterialTheme.colorScheme.surfaceVariant,
        actions = {
            OutlinedButton(
                onClick = { clickListenerAppBar.openNewsOtherAuthor() },
                modifier = modifier.padding(start = 15.dp),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colorScheme.outline)

            ) {
                Text(text = stringResource(id = R.string.titleSearchNews))

            }
        }, floatingActionButton = {
            Row(
                modifier = modifier.padding(end = 10.dp)
            ) {
                IconButton(onClick = { clickListenerAppBar.shareNews() }) {
                    Icon(
                        imageVector = Icons.Outlined.Share,
                        contentDescription = "Share",
                        tint = MaterialTheme.colorScheme.outline
                    )
                }
                IconButton(onClick = { clickListenerAppBar.savedNews() }) {
                    Icon(
                        imageVector = Icons.Outlined.Favorite,
                        tint = if (savedNews) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline,
                        contentDescription = "Add Save"
                    )
                }
                IconButton(onClick = { clickListenerAppBar.moreNews() }) {
                    Icon(
                        imageVector = Icons.Outlined.MoreVert,
                        tint = MaterialTheme.colorScheme.outline,
                        contentDescription = "More"
                    )
                }
            }
        }

    )
}
package com.pasichdev.newshub.ui.components.bottombarviewactivity

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pasichdev.newshub.R

@Composable
fun BottomAppBarViewNews(
    modifier: Modifier = Modifier,
    savedNews: Boolean = false,
    clickListenerAppBar: ClickListenerAppBar

) {
    val isPressed = remember { mutableStateOf(false) }

    BottomAppBar(
        modifier = Modifier
            .height(72.dp)
            .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)),
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        actions = {
            OutlinedButton(
                onClick = { clickListenerAppBar.openNewsOtherAuthor() },
                modifier = modifier.padding(start = 15.dp),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colorScheme.onSurfaceVariant)

            ) {
                Icon(
                    painter = painterResource(id = R.drawable.search_web),
                    contentDescription = stringResource(id = R.string.titleSearchNews),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.size(ButtonDefaults.IconSize))
                Text(
                    text = stringResource(id = R.string.titleSearchNews),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

            }
        }, floatingActionButton = {
            Row(
                modifier = modifier.padding(end = 10.dp)
            ) {
                IconButton(onClick = { clickListenerAppBar.share() }) {
                    Icon(
                        imageVector = Icons.Outlined.Share,
                        contentDescription = "Share",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                IconButton(onClick = { clickListenerAppBar.saved() }) {
                    Icon(
                        imageVector = Icons.Outlined.Favorite,
                        tint = if (savedNews) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
                        contentDescription = "Add Save"
                    )
                }
                IconButton(onClick = {
                    clickListenerAppBar.more()
                    isPressed.value = !isPressed.value
                }) {

                    val icon = if (isPressed.value) {
                        Icons.Filled.Close
                    } else {
                        Icons.Filled.MoreVert
                    }

                    Icon(
                        imageVector = icon,
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        contentDescription = "More"
                    )
                }
            }
        }

    )
}
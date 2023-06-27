package com.pasichdev.newshub.ui.components.moreDialog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pasichdev.newshub.R
import com.pasichdev.newshub.ui.components.bottombarviewactivity.BottomAppBarViewNews
import com.pasichdev.newshub.ui.components.bottombarviewactivity.ClickListenerAppBar

@Composable
fun BottomSheetContent(
    savedNews: Boolean = false, clickListenerAppBar: ClickListenerAppBar
) {
    Column {
        BottomAppBarViewNews(savedNews = savedNews, clickListenerAppBar = clickListenerAppBar)
        BottomSheetListItem(icon = R.drawable.ic_news_open,
            title = stringResource(id = R.string.openLinkBrowser),
            onItemClick = {
                clickListenerAppBar.openBrowser()
            })
        BottomSheetListItem(icon = R.drawable.ic_open_browser,
            title = stringResource(id = R.string.openNewsAuthor),
            onItemClick = {
                clickListenerAppBar.openNewsOtherAuthor()
            })
    }
}

@Composable
fun BottomSheetListItem(icon: Int, title: String, onItemClick: (String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = { onItemClick(title) })
            .height(55.dp)
            .background(color = MaterialTheme.colorScheme.surfaceVariant)
            .padding(start = 15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = "Share",
            tint = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.width(20.dp))
        Text(text = title, color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
}

@Preview(showBackground = true)
@Composable
fun BottomSheetListItemPreview() {
    BottomSheetListItem(icon = R.drawable.ic_saved, title = "Saved") { }
}

@Preview(showBackground = true)
@Composable
fun BottomSheetContentPreview() {
    BottomSheetContent(clickListenerAppBar = object : ClickListenerAppBar {


    })
}
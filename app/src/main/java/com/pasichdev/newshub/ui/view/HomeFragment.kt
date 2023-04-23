package com.pasichdev.newshub.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pasichdev.newshub.R
import com.pasichdev.newshub.ui.theme.titleBoldFont

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .padding(top = 56.dp)
    ) {
        NewsCategoryScreen(nameCategory = "Ukraine")
        NewsCategoryScreen(nameCategory = "Other")
        NewsCategoryScreen(nameCategory = "Recomend")
    }
}


@Composable
fun NewsCategoryScreen(nameCategory: String) {
    Column() {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = nameCategory,
                style = TextStyle(
                    fontFamily = titleBoldFont,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                    lineHeight = 28.sp,
                    letterSpacing = 0.sp
                )
            )
            TextButton(onClick = { /*TODO*/ }) {
                Text(
                    text = stringResource(id = R.string.skip),
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }


        }
        ListNews()
    }


}
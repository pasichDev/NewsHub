package com.pasichdev.newshub.ui.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pasichdev.newshub.R
import com.pasichdev.newshub.ui.theme.itimFontFamily

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .padding(top = 56.dp, bottom = 50.dp)
            .verticalScroll(rememberScrollState())
    ) {
        NewsCategoryScreen(nameCategory = "Ukraine")
        NewsCategoryScreen(nameCategory = "Other")
        NewsCategoryScreen(nameCategory = "Recomend")
    }
}


@Composable
fun NewsCategoryScreen(nameCategory: String) {
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
        ListNews()
    }


}
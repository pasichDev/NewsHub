package com.pasichdev.newshub.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.pasichdev.newshub.ui.theme.itimFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolbarTitleApp() {
    TopAppBar(title = {
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
    })

}
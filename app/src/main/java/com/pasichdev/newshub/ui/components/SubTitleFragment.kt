package com.pasichdev.newshub.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pasichdev.newshub.ui.theme.sansationFontFamily

@Composable
fun SubTitleFragment(modifier: Modifier, textTitle: Int) {

    Text(
        text = stringResource(id = textTitle),
        modifier.padding(vertical = 12.dp),
        fontFamily = sansationFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
    )
}
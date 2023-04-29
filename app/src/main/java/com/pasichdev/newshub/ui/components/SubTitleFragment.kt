package com.pasichdev.newshub.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.pasichdev.newshub.R
import com.pasichdev.newshub.ui.theme.sansationFontFamily

@Composable
fun SubTitleFragment(modifier: Modifier) {
    Text(
        text = stringResource(id = R.string.homeFragmentSubTitle), fontFamily = sansationFontFamily,
        fontWeight = FontWeight.Light,
        fontSize = 18.sp
    )

}
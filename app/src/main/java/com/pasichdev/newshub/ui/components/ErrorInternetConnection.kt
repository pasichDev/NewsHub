package com.pasichdev.newshub.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pasichdev.newshub.R
import com.pasichdev.newshub.ui.theme.AppTheme

@Composable
fun NotInternetConnection(
    modifier: Modifier,
    refresh: () -> Unit = {}
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_error_internet),
            contentDescription = stringResource(id = R.string.internetError),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.surfaceVariant),
            modifier = modifier.padding(bottom = 20.dp)
        )

        Text(
            text = stringResource(id = R.string.internetError),
            style = MaterialTheme.typography.titleMedium
        )

        TextButton(onClick = refresh) {
            Text(
                text = stringResource(id = R.string.refresh),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary,
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppTheme {
        NotInternetConnection(Modifier)
    }
}
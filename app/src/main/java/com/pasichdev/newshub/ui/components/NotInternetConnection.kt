package com.pasichdev.newshub.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pasichdev.newshub.ui.theme.AppTheme

@Composable
fun NotInternetConnection(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Not Internet Connection",
            Modifier.padding(10.dp),
            style = androidx.compose.material3.MaterialTheme.typography.titleLarge
        )

        TextButton(onClick = { /*TODO*/ }) {
            androidx.compose.material3.Text(
                text = "Refresh",
                style = androidx.compose.material3.MaterialTheme.typography.labelMedium
            )
        }

    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppTheme {
        NotInternetConnection()
    }
}
package com.pasichdev.newshub

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowRow
import com.pasichdev.newshub.data.Tags
import com.pasichdev.newshub.data.model.Tag
import com.pasichdev.newshub.ui.theme.AppTheme


class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme() {

                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        ToolbarMainScreen()
        Text(
            text = stringResource(id = R.string.chooseTag),
            style = MaterialTheme.typography.titleMedium,
            modifier = modifier.padding(vertical = 20.dp)
        )
        FilterChipLayout()

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Tag(nameTag: String, icon: Int) {

    FilterChip(selected = false, onClick = { /*TODO*/ }, label = { Text(nameTag) }, leadingIcon = {
        Icon(
            Icons.Filled.Close,
            contentDescription =/*TODO*/ "Localized description",
            modifier = Modifier
                .size(InputChipDefaults.IconSize)
                .clickable { }

        )
    })
}

@SuppressLint("MutableCollectionMutableState")
@Composable
fun FilterChipLayout() {
    val default = remember { Tags.tagsNews }

    val temp: Set<Tag> = emptySet()



    Column(
        modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FilterChipEachRow(chipList = default, tempList = temp)

        ButtonContinue()
    }
}


@Composable
fun ButtonContinue() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        Button(
            onClick = { /* действие кнопки */ },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(text = "Continue", Modifier.padding(10.dp))
        }
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterChipEachRow(
    chipList: List<Tag>, tempList: Set<Tag>
) {
    var multipleChecked by rememberSaveable { mutableStateOf(tempList) }

    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        mainAxisSpacing = 16.dp,
        crossAxisSpacing = 16.dp,
    ) {
        chipList.forEachIndexed { index, tag ->

            FilterChip(selected = multipleChecked.contains(tag), onClick = {
                multipleChecked = if (multipleChecked.contains(tag)) multipleChecked.minus(tag)
                else multipleChecked.plus(tag)

            }, label = {
                Text(
                    text = tag.tagValue, modifier = Modifier.padding(10.dp)
                )
            }, border = FilterChipDefaults.filterChipBorder(
              //  borderColor = if (!multipleChecked.contains(tag)) PurpleGrey40 else Color.Transparent,
                borderWidth = if (multipleChecked.contains(tag)) 0.dp else 1.dp,
            ), shape = RoundedCornerShape(8.dp), leadingIcon = {
                (if (multipleChecked.contains(tag)) Icons.Default.Check else null)?.let {
                    Icon(
                        it, contentDescription = ""
                    )
                }
            })

        }
    }

}


@Composable
fun ToolbarMainScreen() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.titleLarge
        )
        OutlinedButton(onClick = { /*TODO*/ }) {
            Text(
                text = stringResource(id = R.string.skip),
                style = MaterialTheme.typography.labelMedium
            )
        }


    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppTheme {
        MainScreen()
    }
}
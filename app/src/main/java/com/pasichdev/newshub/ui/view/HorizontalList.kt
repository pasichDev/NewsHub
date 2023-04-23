package com.pasichdev.newshub.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pasichdev.newshub.R
import com.pasichdev.newshub.data.News


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ListNews() {
    lateinit var courseList: List<News>
    courseList = ArrayList<News>()

    // in the below line, we are adding data to our list.
    courseList = courseList + News("Android", R.drawable.ic_launcher_background, "News test logic")
    courseList = courseList + News("JavaScript", R.drawable.ic_launcher_background, "")
    courseList = courseList + News("Python", R.drawable.ic_launcher_background, "")
    courseList = courseList + News("C++", R.drawable.ic_launcher_background, "")
    courseList = courseList + News("C#", R.drawable.ic_launcher_background, "")
    courseList = courseList + News("Java", R.drawable.ic_launcher_background, "")
    courseList = courseList + News("Node Js", R.drawable.ic_launcher_background, "")

    LazyRow {
        itemsIndexed(courseList) { position, item ->
            ItemNewsCategory(item)
        }
    }
}


@Composable
fun ItemNewsCategory(news: News) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(5.dp))

            Image(
                painter = painterResource(id = news.image),

                contentDescription = "img",

                modifier = Modifier
                    .height(60.dp)
                    .width(60.dp)
                    .padding(5.dp),

                alignment = Alignment.Center
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = news.title,
                modifier = Modifier.padding(4.dp),
                color = Color.Black, textAlign = TextAlign.Center
            )
        }
    }
}

/*
  // in the below line, we are creating a card for our list view item.
            Card(
                // inside our grid view on below line
                // we are adding on click for each item of our grid view.
                onClick = {
                    // inside on click we are displaying the toast message.
                    Toast.makeText(
                        context,
                        courseList[index].languageName + " selected..",
                        Toast.LENGTH_SHORT
                    ).show()
                },
                // in the below line, we are adding
                // padding from our all sides.
                modifier = Modifier
                    .padding(8.dp)
                    .width(120.dp),

                // in the below line, we are adding
                // elevation for the card.
                elevation = 6.dp
            )
            {
                // in the below line, we are creating
                // a row for our list view item.
                Column(
                    // for our row we are adding modifier
                    // to set padding from all sides.
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // in the below line, inside row we are adding spacer
                    Spacer(modifier = Modifier.height(5.dp))

                    // in the below line, we are adding Image to display the image.
                    Image(
                        // in the below line, we are specifying the drawable image for our image.
                        painter = painterResource(id = courseList[index].languageImg),

                        // in the below line, we are specifying
                        // content description for our image
                        contentDescription = "img",

                        // in the below line, we are setting height
                        // and width for our image.
                        modifier = Modifier
                            .height(60.dp)
                            .width(60.dp)
                            .padding(5.dp),

                        alignment = Alignment.Center
                    )

                    // in the below line, we are adding spacer between image and a text
                    Spacer(modifier = Modifier.height(5.dp))

                    // in the below line, we are creating a text.
                    Text(
                        // inside the text on below line we are
                        // setting text as the language name
                        // from our model class.
                        text = courseList[index].languageName,

                        // in the below line, we are adding padding
                        // for our text from all sides.
                        modifier = Modifier.padding(4.dp),

                        // in the below line, we are adding color for our text
                        color = Color.Black, textAlign = TextAlign.Center
                    )
                }
            }
 */
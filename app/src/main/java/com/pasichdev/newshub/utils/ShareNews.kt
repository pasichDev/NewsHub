package com.pasichdev.newshub.utils

import android.content.Context
import android.content.Intent

fun shareNews(context: Context, url: String) {

    val intent = Intent(Intent.ACTION_SEND)
    intent.type = "text/plain"
    intent.putExtra(Intent.EXTRA_TEXT, url)

    val chooser = Intent.createChooser(intent, "Поділитися через")
    context.startActivity(chooser)

}
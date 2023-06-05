package com.pasichdev.newshub.utils

import android.content.Context
import android.content.Intent
import com.pasichdev.newshub.ui.screen.viewNews.ViewNewsActivity
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

fun openViewNewsActivity(context: Context, url: String, saved: Boolean = false) {

    val intent = Intent(context, ViewNewsActivity::class.java)
    intent.putExtra(
        DETAIL_ARG_NEWS_URL, URLEncoder.encode(
            url, StandardCharsets.UTF_8.toString()
        )
    ).putExtra(
        DETAIL_ARG_SAVED_STATUS, saved
    )
    context.startActivity(intent)

}
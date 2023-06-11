package com.pasichdev.newshub.utils

import android.content.Context
import android.content.Intent
import com.pasichdev.newshub.data.model.News
import com.pasichdev.newshub.ui.screen.viewNews.ViewNewsActivity

fun openViewNewsActivity(context: Context, news: News, saved: Boolean = false) {

    val intent = Intent(context, ViewNewsActivity::class.java)
        .putExtra(DETAIL_ARG_NEWS, news)
        .putExtra(
            DETAIL_ARG_SAVED_STATUS, saved
        )
    context.startActivity(intent)

}
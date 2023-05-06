package com.pasichdev.newshub.data.converters

import androidx.room.TypeConverter
import com.pasichdev.newshub.data.model.News
import com.pasichdev.newshub.data.model.Saved
import com.pasichdev.newshub.data.model.Source


class SavedTypeConverters {


    @TypeConverter
    fun fromNews(news: News): Saved {
        return news.let {
            var description = ""
            if (it.description != null) {
                description = it.description
            }
            Saved(
                it.title, it.author, description, it.url, it.urlToImage, it.publishedAt
            )
        }
    }

    @TypeConverter
    fun toNews(saved: Saved): News {
        return News(
            Source("", ""),
            saved.author,
            saved.title,
            saved.description,
            saved.url,
            saved.urlToImage,
            saved.publishedAt
        )
    }


}
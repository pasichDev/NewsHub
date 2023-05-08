package com.pasichdev.newshub.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pasichdev.newshub.data.converter.SourceConverter
import com.pasichdev.newshub.data.dao.NewsDao
import com.pasichdev.newshub.data.model.News

@Database(
    entities = [News::class], version = 1
)
@TypeConverters(SourceConverter::class)
abstract class LocalDatabase : RoomDatabase() {

    abstract val newsDao: NewsDao
}
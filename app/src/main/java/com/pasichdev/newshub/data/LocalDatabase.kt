package com.pasichdev.newshub.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pasichdev.newshub.data.dao.SavedDao
import com.pasichdev.newshub.data.model.Saved

@Database(
    entities = [Saved::class], version = 1
)
abstract class LocalDatabase : RoomDatabase() {

    abstract val savedDao: SavedDao
}
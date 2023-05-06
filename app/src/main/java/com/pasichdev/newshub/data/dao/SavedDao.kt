package com.pasichdev.newshub.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.pasichdev.newshub.data.model.Saved


@Dao
interface SavedDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun savedNews(saved: Saved);
}
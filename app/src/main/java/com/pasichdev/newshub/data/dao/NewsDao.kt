package com.pasichdev.newshub.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pasichdev.newshub.data.model.News


@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun savedNews(saved: News)

    @Query("SELECT * from saved ORDER BY publishedAt ASC")
    fun loadSavedNews(): LiveData<List<News>>
}
package com.pasichdev.newshub.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pasichdev.newshub.data.model.News
import kotlinx.coroutines.flow.Flow


@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun savedNews(saved: News)

    @Delete
    fun unSavedNews(saved: News)

    @Query("SELECT * from saved ORDER BY publishedAt DESC")
    fun loadSavedNews(): Flow<List<News>>
}
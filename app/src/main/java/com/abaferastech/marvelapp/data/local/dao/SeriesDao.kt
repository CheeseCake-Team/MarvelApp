package com.abaferastech.marvelapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Completable
import com.abaferastech.marvelapp.data.local.entity.SeriesEntity



@Dao
interface SeriesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSeries(series: SeriesEntity):Completable

    @Update
    fun updateSeries(series: SeriesEntity):Completable

    @Query("SELECT * FROM SERIES_TABLE")
    fun getAllSeries(): Observable<List<SeriesEntity>>

    @Query("SELECT * FROM SERIES_TABLE WHERE title=:title")
    fun getSeriesByName(title: String)

    @Query("SELECT * FROM SERIES_TABLE WHERE id=:id")
    fun getSeriesById(id: Int)
}

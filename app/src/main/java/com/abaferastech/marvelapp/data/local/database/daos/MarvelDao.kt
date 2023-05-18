package com.abaferastech.marvelapp.data.local.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.abaferastech.marvelapp.data.local.database.entity.CharacterEntity
import com.abaferastech.marvelapp.data.local.database.entity.ComicEntity
import com.abaferastech.marvelapp.data.local.database.entity.SeriesEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface MarvelDao {

    @Query("SELECT * FROM CHARACTER_TABLE")
    fun getCharacters(): Single<List<CharacterEntity>>


    @Query("SELECT * FROM SERIES_TABLE")
    fun getSeries(): Single<List<SeriesEntity>>


    @Query("SELECT * FROM COMIC_TABLE")
    fun getComics(): Single<List<ComicEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacters(series: List<CharacterEntity>): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertComics(series: List<ComicEntity>): Completable


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSeries(series: List<SeriesEntity>): Completable


}
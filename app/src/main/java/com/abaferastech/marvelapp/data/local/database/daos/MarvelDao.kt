package com.abaferastech.marvelapp.data.local.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.abaferastech.marvelapp.data.local.database.entity.CharacterEntity
import com.abaferastech.marvelapp.data.local.database.entity.ComicEntity
import com.abaferastech.marvelapp.data.local.database.entity.EventEntity
import com.abaferastech.marvelapp.data.local.database.entity.SeriesEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable


@Dao
interface MarvelDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacterList(series: List<CharacterEntity>): Completable


    @Query("SELECT * FROM CHARACTER_TABLE")
    fun getAllCharacters(): List<CharacterEntity>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertComicList(comics: List<ComicEntity>): Completable

    @Query("SELECT * FROM COMIC_TABLE")
    fun getAllComics(): Observable<List<ComicEntity>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEventList(series: List<EventEntity>): Completable

    @Query("SELECT * FROM EVENT_TABLE")
    fun getAllEvents(): Observable<List<EventEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSeriesList(series: List<SeriesEntity>): Completable


    @Query("SELECT * FROM SERIES_TABLE")
    fun getAllSeries(): Observable<List<SeriesEntity>>

}
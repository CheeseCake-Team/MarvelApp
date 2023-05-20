package com.abaferastech.marvelapp.data.local.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.abaferastech.marvelapp.data.local.database.entity.CharacterEntity
import com.abaferastech.marvelapp.data.local.database.entity.ComicEntity
import com.abaferastech.marvelapp.data.local.database.entity.SeriesEntity
import io.reactivex.rxjava3.core.Observable


@Dao
interface MarvelDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacterList(series: List<CharacterEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertComicList(comics: List<ComicEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSeriesList(series: List<SeriesEntity>)

    @Query("DELETE FROM CHARACTER_HOME_TABLE")
    fun deleteAllCharacters()

    @Query("DELETE FROM COMIC_HOME_TABLE")
    fun deleteAllComics()

    @Query("DELETE FROM SERIES_HOME_TABLE")
    fun deleteAllSeries()


    @Query("SELECT * FROM CHARACTER_HOME_TABLE")
    fun getAllCharacters(): Observable<List<CharacterEntity>>


    @Query("SELECT * FROM COMIC_HOME_TABLE")
    fun getAllComics(): Observable<List<ComicEntity>>


    @Query("SELECT * FROM SERIES_HOME_TABLE")
    fun getAllSeries(): Observable<List<SeriesEntity>>

}
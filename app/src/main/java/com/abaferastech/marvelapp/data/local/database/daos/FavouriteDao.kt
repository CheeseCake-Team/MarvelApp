package com.abaferastech.marvelapp.data.local.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.abaferastech.marvelapp.data.local.database.entity.SeriesEntity
import com.abaferastech.marvelapp.data.local.database.entity.favourite.CharacterFavouriteEntity
import com.abaferastech.marvelapp.data.local.database.entity.favourite.ComicFavouriteEntity
import com.abaferastech.marvelapp.data.local.database.entity.favourite.SeriesFavouriteEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single

@Dao
interface FavouriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSeries(series: SeriesFavouriteEntity):Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSeriesList(series: List<SeriesFavouriteEntity>):Completable

    @Update
    fun updateSeries(series: SeriesFavouriteEntity):Completable

    @Query("SELECT * FROM SERIES_FAVOURITE_TABLE")
    fun getAllCashedSeries(): Single<List<SeriesFavouriteEntity>>

    @Query("SELECT * FROM SERIES_FAVOURITE_TABLE WHERE title=:title")
    fun getSeriesByName(title: String): Single<SeriesFavouriteEntity>

    @Query("SELECT * FROM SERIES_FAVOURITE_TABLE WHERE id=:id")
    fun getSeriesById(id: Int): Single<SeriesFavouriteEntity>

    @Delete
    fun deleteSeries(series: SeriesFavouriteEntity):Completable


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacter(character: CharacterFavouriteEntity): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertComic(comic: ComicFavouriteEntity): Completable


    @Delete
    fun deleteCharacter(character: CharacterFavouriteEntity): Completable

    @Delete
    fun deleteComic(comic: ComicFavouriteEntity):  Completable

    @Update
    fun updateCharacter(character: CharacterFavouriteEntity): Completable

    @Query("SELECT * FROM CHARACTER_FAVOURITE_TABLE")
    fun getAllCashedCharacters(): Single<List<CharacterFavouriteEntity>>

    @Query("SELECT * FROM CHARACTER_FAVOURITE_TABLE WHERE id=:id")
    fun getCharacterByIdNullable(id: Int): CharacterFavouriteEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertComicList(series: List<ComicFavouriteEntity>): Completable

    @Update
    fun updateComic(comic: ComicFavouriteEntity): Completable

    @Query("SELECT * FROM COMIC_Favourite_TABLE")
    fun getCachedComics(): Single<List<ComicFavouriteEntity>>

    @Query("SELECT * FROM COMIC_Favourite_TABLE WHERE title=:title")
    fun getComicByName(title: String): Single<ComicFavouriteEntity>

    @Query("SELECT * FROM COMIC_Favourite_TABLE WHERE id=:id")
    fun getComicById(id: Int): Single<ComicFavouriteEntity>



}
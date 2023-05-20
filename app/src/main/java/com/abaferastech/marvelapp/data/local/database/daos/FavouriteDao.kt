package com.abaferastech.marvelapp.data.local.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.abaferastech.marvelapp.data.local.database.entity.favourite.CharacterFavouriteEntity
import com.abaferastech.marvelapp.data.local.database.entity.favourite.ComicFavouriteEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single

@Dao
interface FavouriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacter(character: CharacterFavouriteEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertComic(comic: ComicFavouriteEntity)


    @Delete
    fun deleteCharacter(character: CharacterFavouriteEntity)

    @Delete
    fun deleteComic(comic: ComicFavouriteEntity):  Completable

    @Update
    fun updateCharacter(character: CharacterFavouriteEntity)

    @Query("SELECT * FROM CHARACTER_FAVOURITE_TABLE")
    fun getAllCashedCharacters(): Single<List<CharacterFavouriteEntity>>

    @Query("SELECT * FROM CHARACTER_FAVOURITE_TABLE WHERE id=:id")
    fun getCharacterByIdNullable(id: Int): CharacterFavouriteEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertComicList(series: List<ComicFavouriteEntity>)

    @Update
    fun updateComic(comic: ComicFavouriteEntity)

    @Query("SELECT * FROM COMIC_Favourite_TABLE")
    fun getCachedComics(): Single<List<ComicFavouriteEntity>>

    @Query("SELECT * FROM COMIC_Favourite_TABLE WHERE title=:title")
    fun getComicByName(title: String): Single<ComicFavouriteEntity>

    @Query("SELECT * FROM COMIC_Favourite_TABLE WHERE id=:id")
    fun getComicById(id: Int): Single<ComicFavouriteEntity>



}
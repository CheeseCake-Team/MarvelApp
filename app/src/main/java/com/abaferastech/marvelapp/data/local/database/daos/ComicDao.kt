package com.abaferastech.marvelapp.data.local.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.abaferastech.marvelapp.data.local.database.entity.ComicEntity
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface ComicDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertComic(comic: ComicEntity): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertComicList(series: List<ComicEntity>):Completable


    @Query("SELECT * FROM COMIC_TABLE")
    fun getAllComics(): Single<List<ComicEntity>>
    @Update
    fun updateComic(comic: ComicEntity): Completable

    @Query("SELECT * FROM COMIC_TABLE WHERE title=:title")
    fun getComicByName(title: String): Single<ComicEntity>

    @Query("SELECT * FROM COMIC_TABLE WHERE id=:id")
    fun getComicById(id: Int): Single<ComicEntity>
}
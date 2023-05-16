package com.abaferastech.marvelapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.abaferastech.marvelapp.data.local.entity.ComicEntity
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Completable

@Dao
interface ComicDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertComic(comic: ComicEntity): Completable

    @Update
    fun updateComic(comic: ComicEntity): Completable

    @Query("SELECT * FROM COMIC_TABLE")
    fun getAllComics(): Observable<List<ComicEntity>>
}
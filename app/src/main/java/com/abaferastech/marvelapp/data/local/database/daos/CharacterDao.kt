package com.abaferastech.marvelapp.data.local.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.abaferastech.marvelapp.data.local.database.entity.CharacterEntity
import com.abaferastech.marvelapp.data.local.database.entity.ComicEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacter(character: CharacterEntity): Completable

    @Delete
    fun deleteCharacter(character: CharacterEntity):Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertComic(comic: ComicEntity): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacterList(series: List<CharacterEntity>):Completable

    @Update
    fun updateCharacter(character: CharacterEntity): Completable

    @Query("SELECT * FROM CHARACTER_TABLE")
    fun getAllCashedCharacters(): Single<List<CharacterEntity>>

    @Query("SELECT * FROM CHARACTER_TABLE WHERE id=:id")
    fun getCharacterById(id: Int): Single<CharacterEntity>
}

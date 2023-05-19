package com.abaferastech.marvelapp.data.local.database.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.abaferastech.marvelapp.data.local.database.entity.CharacterEntity
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacter(character: CharacterEntity): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacterList(series: List<CharacterEntity>):Completable

    @Delete
    fun deleteCharacter(character: CharacterEntity):Completable

    @Update
    fun updateCharacter(character: CharacterEntity): Completable

    @Query("SELECT * FROM CHARACTER_TABLE")
    fun getAllCharacters(): Single<List<CharacterEntity>>

    @Query("SELECT * FROM CHARACTER_TABLE WHERE id=:id")
    fun getCharacterById(id: Int): Observable<CharacterEntity>
}

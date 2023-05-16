package com.abaferastech.marvelapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.abaferastech.marvelapp.data.local.entity.CharacterEntity
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Completable

@Dao
interface CharacterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacter(character: CharacterEntity): Completable

    @Update
    fun updateCharacter(character: CharacterEntity): Completable

    @Query("SELECT * FROM CHARACTER_TABLE")
    fun getAllCharacters(): Observable<List<CharacterEntity>>
}

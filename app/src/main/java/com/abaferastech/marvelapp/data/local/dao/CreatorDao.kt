package com.abaferastech.marvelapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.abaferastech.marvelapp.data.local.entity.CreatorEntity
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Completable

@Dao
interface CreatorDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCreator(creator: CreatorEntity): Completable

    @Update
    fun updateCreator(creator: CreatorEntity): Completable

    @Query("SELECT * FROM CREATOR_TABLE")
    fun getAllCreators(): Observable<List<CreatorEntity>>

    @Query("SELECT * FROM CREATOR_TABLE WHERE fullName=:fullName")
    fun getCreatorByName(fullName: String)

    @Query("SELECT * FROM CREATOR_TABLE WHERE id=:id")
    fun getCreatorById(id: Int)
}


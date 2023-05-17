package com.abaferastech.marvelapp.data.local.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.abaferastech.marvelapp.data.local.database.entity.CreatorEntity
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface CreatorDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCreator(creator: CreatorEntity): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCreatorList(series: List<CreatorEntity>):Completable

    @Update
    fun updateCreator(creator: CreatorEntity): Completable

    @Query("SELECT * FROM CREATOR_TABLE")
    fun getAllCreators(): Observable<List<CreatorEntity>>

    @Query("SELECT * FROM CREATOR_TABLE WHERE fullName=:fullName")
    fun getCreatorByName(fullName: String): Single<CreatorEntity>

    @Query("SELECT * FROM CREATOR_TABLE WHERE id=:id")
    fun getCreatorById(id: Int): Single<CreatorEntity>
}


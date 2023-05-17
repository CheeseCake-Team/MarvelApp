package com.abaferastech.marvelapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.abaferastech.marvelapp.data.local.entity.EventEntity
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Completable


@Dao
interface EventDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(event: EventEntity): Completable

    @Update
    fun update(event: EventEntity): Completable

    @Query("SELECT * FROM EVENT_TABLE")
    fun getAllEvents(): Observable<List<EventEntity>>

    @Query("SELECT * FROM EVENT_TABLE WHERE title=:title")
    fun getEventByName(title: String)

    @Query("SELECT * FROM EVENT_TABLE WHERE id=:id")
    fun getEventById(id: Int)
}


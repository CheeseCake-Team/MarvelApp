package com.abaferastech.marvelapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.abaferastech.marvelapp.data.local.dao.CharacterDao
import com.abaferastech.marvelapp.data.local.dao.ComicDao
import com.abaferastech.marvelapp.data.local.dao.CreatorDao
import com.abaferastech.marvelapp.data.local.dao.EventDao
import com.abaferastech.marvelapp.data.local.dao.SeriesDao
import com.abaferastech.marvelapp.data.local.entity.CharacterEntity
import com.abaferastech.marvelapp.data.local.entity.ComicEntity
import com.abaferastech.marvelapp.data.local.entity.CreatorEntity
import com.abaferastech.marvelapp.data.local.entity.EventEntity
import com.abaferastech.marvelapp.data.local.entity.SeriesEntity
import com.abaferastech.marvelapp.utilities.Converters

@Database(
    entities =
    [CharacterEntity::class,
        ComicEntity::class,
        CreatorEntity::class,
        EventEntity::class,
        SeriesEntity::class], version = 1)

@TypeConverters(Converters::class)
abstract class MarvelDatabase : RoomDatabase() {

    abstract fun characterDao(): CharacterDao
    abstract fun comicDao(): ComicDao
    abstract fun creatorDao(): CreatorDao
    abstract fun eventDao(): EventDao
    abstract fun seriesDao(): SeriesDao
}




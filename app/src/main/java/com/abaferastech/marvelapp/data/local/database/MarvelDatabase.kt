package com.abaferastech.marvelapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.abaferastech.marvelapp.data.local.database.daos.CharacterDao
import com.abaferastech.marvelapp.data.local.database.daos.ComicDao
import com.abaferastech.marvelapp.data.local.database.daos.CreatorDao
import com.abaferastech.marvelapp.data.local.database.daos.EventDao
import com.abaferastech.marvelapp.data.local.database.daos.SeriesDao
import com.abaferastech.marvelapp.data.local.database.entity.CharacterEntity
import com.abaferastech.marvelapp.data.local.database.entity.ComicEntity
import com.abaferastech.marvelapp.data.local.database.entity.CreatorEntity
import com.abaferastech.marvelapp.data.local.database.entity.EventEntity
import com.abaferastech.marvelapp.data.local.database.entity.SeriesEntity

@Database(
    entities = [CharacterEntity::class, ComicEntity::class, CreatorEntity::class,
        EventEntity::class, SeriesEntity::class],
    version = 1
)

@TypeConverters(Converters::class)
abstract class MarvelDatabase : RoomDatabase() {

    abstract fun characterDao(): CharacterDao
    abstract fun comicDao(): ComicDao
    abstract fun creatorDao(): CreatorDao
    abstract fun eventDao(): EventDao
    abstract fun seriesDao(): SeriesDao
}




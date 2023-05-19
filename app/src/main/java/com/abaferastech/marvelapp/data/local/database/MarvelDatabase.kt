package com.abaferastech.marvelapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.abaferastech.marvelapp.data.local.database.daos.CharacterDao
import com.abaferastech.marvelapp.data.local.database.daos.ComicDao
import com.abaferastech.marvelapp.data.local.database.daos.SearchQueryDao
import com.abaferastech.marvelapp.data.local.database.daos.SeriesDao
import com.abaferastech.marvelapp.data.local.database.entity.CharacterEntity
import com.abaferastech.marvelapp.data.local.database.entity.ComicEntity
import com.abaferastech.marvelapp.data.local.database.entity.EventEntity
import com.abaferastech.marvelapp.data.local.database.entity.SearchQueryEntity
import com.abaferastech.marvelapp.data.local.database.entity.SeriesEntity

@Database(
    entities = [CharacterEntity::class, ComicEntity::class,SearchQueryEntity::class,
        EventEntity::class, SeriesEntity::class],
    version = 2
)

abstract class MarvelDatabase : RoomDatabase() {

    abstract fun characterDao(): CharacterDao
    abstract fun comicDao(): ComicDao
    abstract fun seriesDao(): SeriesDao
    abstract fun searchQueryDoa(): SearchQueryDao
}




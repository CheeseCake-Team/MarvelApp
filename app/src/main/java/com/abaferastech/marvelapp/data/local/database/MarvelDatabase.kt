package com.abaferastech.marvelapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.abaferastech.marvelapp.data.local.database.daos.MarvelDao
import com.abaferastech.marvelapp.data.local.database.daos.SearchDao
import com.abaferastech.marvelapp.data.local.database.entity.CharacterEntity
import com.abaferastech.marvelapp.data.local.database.entity.ComicEntity
import com.abaferastech.marvelapp.data.local.database.entity.SearchQueryEntity
import com.abaferastech.marvelapp.data.local.database.entity.SeriesEntity
import com.abaferastech.marvelapp.data.local.database.entity.search.CharacterSearchEntity
import com.abaferastech.marvelapp.data.local.database.entity.search.ComicSearchEntity
import com.abaferastech.marvelapp.data.local.database.entity.search.EventSearchEntity
import com.abaferastech.marvelapp.data.local.database.entity.search.SeriesSearchEntity

@Database(
    entities = [
        CharacterEntity::class, ComicEntity::class, CharacterSearchEntity::class,
        SeriesEntity::class, SearchQueryEntity::class, ComicSearchEntity::class,
        EventSearchEntity::class, SeriesSearchEntity::class
    ],
    version = 1
)

abstract class MarvelDatabase : RoomDatabase() {
    abstract fun marvelDao(): MarvelDao
    abstract fun searchQueryDoa(): SearchDao
}




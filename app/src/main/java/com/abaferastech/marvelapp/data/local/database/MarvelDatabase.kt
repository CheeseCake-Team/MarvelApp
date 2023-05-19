package com.abaferastech.marvelapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.abaferastech.marvelapp.data.local.database.daos.MarvelDao
import com.abaferastech.marvelapp.data.local.database.daos.SearchQueryDao
import com.abaferastech.marvelapp.data.local.database.entity.CharacterEntity
import com.abaferastech.marvelapp.data.local.database.entity.ComicEntity
import com.abaferastech.marvelapp.data.local.database.entity.CreatorEntity
import com.abaferastech.marvelapp.data.local.database.entity.EventEntity
import com.abaferastech.marvelapp.data.local.database.entity.SearchQueryEntity
import com.abaferastech.marvelapp.data.local.database.entity.SeriesEntity
import javax.inject.Inject

@Database(
    entities = [
        CharacterEntity::class, ComicEntity::class,
        EventEntity::class, SeriesEntity::class, SearchQueryEntity::class
    ],
    version = 4
)

abstract class MarvelDatabase : RoomDatabase() {
    abstract fun marvelDao(): MarvelDao
    abstract fun searchQueryDoa(): SearchQueryDao
}




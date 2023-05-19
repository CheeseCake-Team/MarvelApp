package com.abaferastech.marvelapp.di

import android.content.Context
import androidx.room.Room
import com.abaferastech.marvelapp.data.local.database.MarvelDatabase
import com.abaferastech.marvelapp.data.local.database.daos.MarvelDao
import com.abaferastech.marvelapp.data.local.database.daos.SearchDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideMarvelDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, MarvelDatabase::class.java, "MARVEL_DATABASE").build()


    @Singleton
    @Provides
    fun provideMarvelDao(marvelDatabase: MarvelDatabase): MarvelDao {
        return marvelDatabase.marvelDao()
    }

    @Singleton
    @Provides
    fun provideSearchDao(marvelDatabase: MarvelDatabase): SearchDao {
        return marvelDatabase.searchQueryDoa()
    }

}
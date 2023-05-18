package com.abaferastech.marvelapp.di

import android.content.Context
import androidx.room.Room
import com.abaferastech.marvelapp.data.local.database.MarvelDatabase
import com.abaferastech.marvelapp.data.local.database.daos.CharacterDao
import com.abaferastech.marvelapp.data.local.database.daos.ComicDao
import com.abaferastech.marvelapp.data.local.database.daos.SearchQueryDao
import com.abaferastech.marvelapp.data.local.database.daos.SeriesDao
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
    fun provideCharacterDao(marvelDatabase: MarvelDatabase): CharacterDao {
        return marvelDatabase.characterDao()
    }

    @Singleton
    @Provides
    fun provideSearchQueryDao(marvelDatabase: MarvelDatabase): SearchQueryDao {
        return marvelDatabase.searchQueryDoa()
    }

    @Singleton
    @Provides
    fun provideComicDao(marvelDatabase: MarvelDatabase): ComicDao {
        return marvelDatabase.comicDao()
    }

    @Singleton
    @Provides
    fun provideSeriesDao(marvelDatabase: MarvelDatabase): SeriesDao {
        return marvelDatabase.seriesDao()
    }

}
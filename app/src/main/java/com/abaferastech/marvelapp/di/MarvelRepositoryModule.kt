package com.abaferastech.marvelapp.di

import com.abaferastech.marvelapp.data.local.database.daos.CharacterDao
import com.abaferastech.marvelapp.data.local.database.daos.SearchQueryDao
import com.abaferastech.marvelapp.data.remote.MarvelApiService
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object MarvelRepositoryModule {

    @Provides
    @Singleton
    fun provideMarvelRepository(characterDao: CharacterDao,
                                searchQueryDao: SearchQueryDao,
                                apiService: MarvelApiService)
            : MarvelRepository {
        return MarvelRepository(characterDao, searchQueryDao, apiService)
    }

}

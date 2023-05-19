package com.abaferastech.marvelapp.di

import com.abaferastech.marvelapp.data.local.database.daos.CharacterDao
import com.abaferastech.marvelapp.data.remote.MarvelApiService
import com.abaferastech.marvelapp.data.repository.IMarvelRepository
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.domain.mapper.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(ViewModelComponent::class)
object MarvelRepositoryModule {

    @Provides
    @Singleton
    fun provideMarvelRepository(apiService: MarvelApiService,
                                comicMapper: ComicDominMapper,
                                seriesMapper: SeriesMapper,
                                creatorMapper: CreatorMapper,
                                eventMapper: EventMapper,
                                characterDomainMapper: CharacterDomainMapper
    ): IMarvelRepository {
        return MarvelRepository(
            apiService,
            comicMapper,
            seriesMapper,
            creatorMapper,
            eventMapper,
            characterDomainMapper
        )
    }
}
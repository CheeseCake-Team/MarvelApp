package com.abaferastech.marvelapp.di

import com.abaferastech.marvelapp.data.repository.IMarvelRepository
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
abstract class MarvelRepositoryModule {
    @Binds
    @ViewModelScoped
    abstract fun bindMarvelRepository(marvelRepository: MarvelRepository): IMarvelRepository
}

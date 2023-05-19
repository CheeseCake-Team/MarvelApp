package com.abaferastech.marvelapp.ui.favourites

import androidx.lifecycle.ViewModel
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(
    private val repository: MarvelRepository
): BaseViewModel() {

}
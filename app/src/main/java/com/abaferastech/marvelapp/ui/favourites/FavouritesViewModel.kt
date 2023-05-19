package com.abaferastech.marvelapp.ui.favourites

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModel
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(
    private val repository: MarvelRepository
) : BaseViewModel() {

    init {
        getAllCharacters()
    }

    @SuppressLint("CheckResult")
    fun getAllCharacters() {
        Log.i( "getAllCharacters: ", repository.getAllEntityCharacters().toString())
        repository.getAllEntityCharacters().subscribe { s ->
            Log.i("ebrabw", "getAllCharacters: $s")
        }
    }


}
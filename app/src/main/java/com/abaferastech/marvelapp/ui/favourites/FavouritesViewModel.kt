package com.abaferastech.marvelapp.ui.favourites

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.domain.models.Character
import com.abaferastech.marvelapp.ui.base.BaseInteractionListener
import com.abaferastech.marvelapp.ui.base.BaseViewModel
import com.abaferastech.marvelapp.ui.character.characters.CharactersInteractionListener
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(
    private val repository: MarvelRepository
) : BaseViewModel(), BaseInteractionListener, CharactersInteractionListener {

    private val _allCharacters = MutableLiveData<List<Character>>()
    val allCharacters: LiveData<List<Character>> get() = _allCharacters

    init {
        getCachedCharacters()
    }

    @SuppressLint("CheckResult")
    fun getCachedCharacters() {
        Log.i( "getAllCharacters: ", repository.getAllCashedCharacters().toString())
        repository.getAllCashedCharacters().subscribe { items ->
           _allCharacters.postValue(items)
        }
    }

    override fun onClickCharacter(character: Character) {
        TODO("Not yet implemented")
    }


}
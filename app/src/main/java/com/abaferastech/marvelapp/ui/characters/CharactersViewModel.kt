package com.abaferastech.marvelapp.ui.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.abaferastech.marvelapp.data.model.result.Characters
import com.abaferastech.marvelapp.ui.model.UIState
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.ui.base.BaseViewModel

class CharactersViewModel : BaseViewModel() {
    private val repository = MarvelRepository()


    private val _characters = MutableLiveData<UIState<List<Characters>>>()
    val characters: LiveData<UIState<List<Characters>>> get() = _characters

    init {
        getAllCharacters()
    }
    private fun getAllCharacters() {
        repository.getAllCharacters()
            .applySchedulersAndPostUIStates(_characters::postValue)
    }

    fun getEventCharacter(characterId: Int) {
        repository.getEventCharacters(characterId)
            .applySchedulersAndPostUIStates(_characters::postValue)
    }

    fun getCharacterComics(characterId: Int) {
        repository.getComicCharacters(characterId)
            .applySchedulersAndPostUIStates(_characters::postValue)
    }
    fun getCharacterSeries(characterId: Int) {
        repository.getSeriesCharacters(characterId)
            .applySchedulersAndPostUIStates(_characters::postValue)
    }


}
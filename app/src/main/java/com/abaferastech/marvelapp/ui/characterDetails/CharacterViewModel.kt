package com.abaferastech.marvelapp.ui.characterDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.abaferastech.marvelapp.data.model.result.Characters
import com.abaferastech.marvelapp.data.model.result.Comics
import com.abaferastech.marvelapp.data.model.result.Events
import com.abaferastech.marvelapp.data.model.result.Series
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.ui.base.BaseViewModel
import com.abaferastech.marvelapp.ui.model.UIState

class CharacterViewModel : BaseViewModel() {
    private val repository = MarvelRepository()

    private val _character = MutableLiveData<UIState<Characters>>()
    val character: LiveData<UIState<Characters>> = _character

    private val _characterEvents = MutableLiveData<UIState<List<Events>>>()
    val characterEvents: LiveData<UIState<List<Events>>> get() = _characterEvents

    private val _characterComics = MutableLiveData<UIState<List<Comics>>>()
    val characterComics: LiveData<UIState<List<Comics>>> get() = _characterComics

    private val _characterSeries = MutableLiveData<UIState<List<Series>>>()
    val characterSeries: LiveData<UIState<List<Series>>> get() = _characterSeries



    fun getSingleCharacter(characterId: Int) {
        repository.getSingleCharacter(characterId)
            .applySchedulersAndPostUIStates(_character::postValue)
    }

    fun getCharacterEvents(characterId: Int) {
        repository.getCharacterEvents(characterId)
            .applySchedulersAndPostUIStates(_characterEvents::postValue)
    }

    fun getCharacterComics(characterId: Int) {
        repository.getCharacterComics(characterId)
            .applySchedulersAndPostUIStates(_characterComics::postValue)
    }
    fun getCharacterSeries(characterId: Int) {
        repository.getCharacterSeries(characterId)
            .applySchedulersAndPostUIStates(_characterSeries::postValue)
    }


}
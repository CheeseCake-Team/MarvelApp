package com.abaferastech.marvelapp.ui.characterDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.abaferastech.marvelapp.data.model.result.Characters
import com.abaferastech.marvelapp.ui.model.UIState
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.ui.base.BaseViewModel

class CharacterViewModel : BaseViewModel() {
    private val repository = MarvelRepository()

    private val _character = MutableLiveData<UIState<Characters>>()
    val character: LiveData<UIState<Characters>> = _character


    fun getSingleCharacter(characterId: Int) {
        repository.getSingleCharacter(characterId)
            .applySchedulersAndSubscribe(_character::postValue)
    }

}
package com.abaferastech.marvelapp.ui.character.characterDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.abaferastech.marvelapp.data.domain.models.Character
import com.abaferastech.marvelapp.data.mapper.dtotodomain.CharacterMapper
import com.abaferastech.marvelapp.data.remote.response.CharacterDTO
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.ui.base.BaseViewModel
import com.abaferastech.marvelapp.ui.model.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    private val repository: MarvelRepository,
    savedStateHandle: SavedStateHandle
) : BaseViewModel(savedStateHandle) {



    private val _character = MutableLiveData<Character>()
    val character: LiveData<Character> = _character

    init {
        val characterId = getSavedStateValue<Int>("characterId")
        characterId?.let {
            getSingleCharacter(it)
        }
    }

    fun saveCharacterId(passedId: Int){
        setSavedStateValue("characterId",passedId)
    }

    private fun getSingleCharacter(passedId: Int) {
        repository.getSingleCharacter(passedId).applySchedulersAndPostUIStates {
            _character.postValue(CharacterMapper().map(it.toData()!!))
        }
    }

}
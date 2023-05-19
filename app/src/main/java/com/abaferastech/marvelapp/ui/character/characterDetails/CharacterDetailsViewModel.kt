package com.abaferastech.marvelapp.ui.character.characterDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.ui.base.BaseViewModel
import com.abaferastech.marvelapp.ui.model.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import com.abaferastech.marvelapp.domain.models.Character
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor( val repository:MarvelRepository , state: SavedStateHandle) : BaseViewModel() {

    val characterArgs = state.let {
        CharacterDetailsFragmentArgs.fromSavedStateHandle(it)
    }

    var isFavouriteClicked = MutableLiveData<Boolean>()

    private val _character = MutableLiveData<UIState<Character>>()
    val character: LiveData<UIState<Character>> = _character

    fun getSingleCharacter(passedId: Int? = null) {
        val characterId = passedId ?: characterArgs.characterId
        repository.getSingleCharacter(characterId)
            .applySchedulersAndPostUIStates(_character::postValue)
    }

}
package com.abaferastech.marvelapp.ui.character.characterDetails

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.abaferastech.marvelapp.data.remote.response.CharacterDTO
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.domain.models.Series
import com.abaferastech.marvelapp.ui.base.BaseViewModel
import com.abaferastech.marvelapp.domain.models.Character
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

     val allCharacters = MutableLiveData<List<Character>>()

     val _isCharacterFavourite = MutableLiveData<Boolean>(false)
    val isCharacterFavourite : LiveData<Boolean> get() = _isCharacterFavourite

    fun getSingleCharacter(passedId: Int? = null) {
        val characterId = passedId ?: characterArgs.characterId
        repository.getSingleCharacter(characterId)
            .applySchedulersAndPostUIStates(_character::postValue)
    }

    private fun checkIfFavourite() {
        val characterId = character.value?.toData()?.id
        Log.i("ebrabw: ",characterId.toString())
        _isCharacterFavourite.postValue(allCharacters.value?.any { it.id == characterId })
    }

    init {
        getAllCharacters()
        checkIfFavourite()
    }

    fun insertCharacter() {
        character.value?.toData()?.apply { isFavourite = true }?.let { repository.insertCharacter(it) }
    }

    fun deleteCharacter() {
        character.value?.toData()?.let { repository.deleteCharacter(it) }
    }

    @SuppressLint("CheckResult")
    fun getAllCharacters() {
        Log.i( "getAllCharacters: ", repository.getAllCashedCharacters().toString())
        repository.getAllCashedCharacters().subscribe { characterList ->
            Log.i("ebrabw", "getAllCharacters: $characterList")
            allCharacters.postValue(characterList)
            Log.i("ebrabw", "getAllCharacters: ${allCharacters.value}")
        }
    }


}
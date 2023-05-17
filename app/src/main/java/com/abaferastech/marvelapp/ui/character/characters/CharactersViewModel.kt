package com.abaferastech.marvelapp.ui.character.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.abaferastech.marvelapp.data.remote.response.CharacterDTO
import com.abaferastech.marvelapp.ui.model.UIState
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.ui.base.BaseViewModel
import com.abaferastech.marvelapp.ui.model.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val repository:MarvelRepository
) : BaseViewModel(), CharactersInteractionListener {


    private val _characters = MutableLiveData<UIState<List<CharacterDTO>>>()
    val characters: LiveData<UIState<List<CharacterDTO>>> get() = _characters

    val navigationEvents = MutableLiveData<Event<CharacterEvent>>()


    fun getAllCharacters() {
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

    override fun onClickCharacter(character: CharacterDTO) {
        navigationEvents.postValue(Event(CharacterEvent.ClickCharacterEvent(character.id!!)))
    }

}
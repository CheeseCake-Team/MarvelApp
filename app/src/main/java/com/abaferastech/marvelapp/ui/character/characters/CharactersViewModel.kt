package com.abaferastech.marvelapp.ui.character.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.abaferastech.marvelapp.data.domain.models.Character
import com.abaferastech.marvelapp.data.mapper.dtotodomain.CharacterMapper
import com.abaferastech.marvelapp.data.mapper.dtotodomain.ComicMapper
import com.abaferastech.marvelapp.data.remote.response.CharacterDTO
import com.abaferastech.marvelapp.data.remote.response.ComicDTO
import com.abaferastech.marvelapp.ui.model.UIState
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.ui.base.BaseViewModel
import com.abaferastech.marvelapp.ui.model.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val repository: MarvelRepository,
    savedStateHandle: SavedStateHandle
) : BaseViewModel(savedStateHandle), CharactersInteractionListener {

    private val _characters = MutableLiveData<List<Character>>()
    val characters: LiveData<List<Character>> get() = _characters

    val navigationEvents = MutableLiveData<Event<CharacterEvent>>()


    fun savePassedCharacterId(passedId: Int) {
        setSavedStateValue("characterId", passedId)
    }

    private fun getPassedId() = getSavedStateValue<Int>("characterId")

    fun getAllCharacters() {
        fetchItemsList {
            repository.getAllCharacters()
        }
    }



    fun getEventCharacter() {
        fetchItemsList {
            repository.getEventCharacters(getPassedId()!!)
        }
    }

    fun getCharacterComics() {
        fetchItemsList {
            repository.getComicCharacters(getPassedId()!!)
        }
    }

    fun getCharacterSeries() {
        fetchItemsList {
            repository.getSeriesCharacters(getPassedId()!!)
        }
    }

    override fun onClickCharacter(character: Character) {
        navigationEvents.postValue(Event(CharacterEvent.ClickCharacterEvent(character.id)))
    }

    private fun fetchItemsList(getItemsList: () -> Single<UIState<List<CharacterDTO>>>) {
        getItemsList()
            .applySchedulersAndPostUIStates { dtoList ->
                _characters.postValue(convertDtoToListDomain(dtoList.toData()!!))
            }
    }
    private fun convertDtoToListDomain(list: List<CharacterDTO>): MutableList<Character> {
        val result = mutableListOf<Character>()
        list.forEach {
            result.add(CharacterMapper().map(it))
        }
        return result
    }

}
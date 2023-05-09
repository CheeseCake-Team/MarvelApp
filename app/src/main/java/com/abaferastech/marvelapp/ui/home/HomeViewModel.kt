package com.abaferastech.marvelapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.abaferastech.marvelapp.data.model.result.Characters
import com.abaferastech.marvelapp.data.model.result.Comics
import com.abaferastech.marvelapp.data.model.result.Series
import com.abaferastech.marvelapp.data.model.uimodel.DataItem
import com.abaferastech.marvelapp.data.model.uimodel.Tag
import com.abaferastech.marvelapp.data.model.uimodel.UIState
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.ui.base.BaseViewModel
import com.abaferastech.marvelapp.ui.characters.CharactersInteractionListener
import com.abaferastech.marvelapp.ui.home.adapters.ComicsInteractionListener
import com.abaferastech.marvelapp.ui.home.adapters.SeriesInteractionListener

class HomeViewModel : BaseViewModel(), ComicsInteractionListener, CharactersInteractionListener,
    SeriesInteractionListener {
    private val repository = MarvelRepository()

    private val _homeData = MediatorLiveData<List<DataItem>>()
    val homeData: LiveData<List<DataItem>> get() = _homeData

    private val _characters = MutableLiveData<UIState<List<Characters>>>()
    private val _comics = MutableLiveData<UIState<List<Comics>>>()
    private val _series = MutableLiveData<UIState<List<Series>>>()


    private val _isCharacterClicked = MutableLiveData<Boolean>()
    val isCharacterClicked: LiveData<Boolean> get() = _isCharacterClicked

    private val _selectedCharacterID = MutableLiveData<Int>()
    val selectedCharacterID: LiveData<Int> get() = _selectedCharacterID


    init {
        _homeData.addSource(_characters) { updateHomeData() }
        _homeData.addSource(_comics) { updateHomeData() }
        _homeData.addSource(_series) { updateHomeData() }

        repository.getAllCharacters()
            .applySchedulersAndSubscribe(_characters::postValue)

        repository.getAllComics().applySchedulersAndSubscribe(_comics::postValue)

        repository.getAllSeries().applySchedulersAndSubscribe(_series::postValue)

    }

    private fun updateHomeData() {

        val characters = _characters.value?.toData() ?: emptyList()
        val comics = _comics.value?.toData() ?: emptyList()
        val series = _series.value?.toData() ?: emptyList()

        val data = listOf(
            DataItem.HeaderItem(characters.shuffled().take(3), 0),
            DataItem.CharacterTagItem(
                Tag("CHARACTERS", characters.shuffled()), 1, this
            ),
            DataItem.ComicsTagItem(Tag("COMICS", comics.shuffled()), 2, this),
            DataItem.SeriesTagItem(Tag("SERIES", series.shuffled()), 3, this)
        )
        _homeData.postValue(data)
    }

    override fun onCleared() {
        super.onCleared()
        _homeData.removeSource(_characters)
        _homeData.removeSource(_comics)
        _homeData.removeSource(_series)
    }


    private fun onCharactersError(errorMessage: Throwable) {
        _characters.postValue(UIState.Error(errorMessage.message.toString()))
    }

    private fun onComicsError(errorMessage: Throwable) {
        _comics.postValue(UIState.Error(errorMessage.message.toString()))
    }

    private fun onSeriesError(errorMessage: Throwable) {
        _series.postValue(UIState.Error(errorMessage.message.toString()))
    }

    override fun onClickCharacter(character: Characters) {
        _isCharacterClicked.postValue(true)
        _selectedCharacterID.postValue(character.id!!)

    }


    override fun onClickSeries(series: Series) {
        TODO("Not yet implemented")
    }

    override fun onClickComics(comics: Comics) {
        TODO("Not yet implemented")
    }

    fun resetCharacterClickStatus() {
        _isCharacterClicked.value = false
    }
}
package com.abaferastech.marvelapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.data.model.result.Characters
import com.abaferastech.marvelapp.data.model.result.Comics
import com.abaferastech.marvelapp.data.model.result.Series
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.ui.base.BaseViewModel
import com.abaferastech.marvelapp.ui.characters.CharactersInteractionListener
import com.abaferastech.marvelapp.ui.home.adapters.ComicsInteractionListener
import com.abaferastech.marvelapp.ui.home.adapters.SeriesInteractionListener
import com.abaferastech.marvelapp.ui.model.*

class HomeViewModel : BaseViewModel(), ComicsInteractionListener, CharactersInteractionListener,
    SeriesInteractionListener {
    private val repository = MarvelRepository()

    private val _homeData = MediatorLiveData<UIState<List<DataItem>>>()
    val homeData: MediatorLiveData<UIState<List<DataItem>>> get() = _homeData

    private val _characters = MutableLiveData<UIState<List<Characters>>>()
    private val _comics = MutableLiveData<UIState<List<Comics>>>()
    private val _series = MutableLiveData<UIState<List<Series>>>()

    val navigationEvent = MutableLiveData<Event<TYPE>>()





    private val _selectedItemID = MutableLiveData<Int>()
    val selectedItemID: LiveData<Int> get() = _selectedItemID

    override fun onClickCharacter(character: Characters) {
        _selectedItemID.postValue(character.id!!)
        navigationEvent.postValue( NavigationEvent(TYPE.CHARACTER))
    }

    override fun onClickSeries(series: Series) {
        _selectedItemID.postValue(series.id)
        navigationEvent.postValue( NavigationEvent(TYPE.SERIES))
    }

    override fun onClickComics(comics: Comics) {
        _selectedItemID.postValue(comics.id!!)
        navigationEvent.postValue( NavigationEvent(TYPE.COMIC))
    }


    //region
    val data = mutableListOf<DataItem>()

    init {
        _homeData.addSource(_characters) {
            updateCharacterDataItem()
            checkAllDataSourcesUpdated()
        }
        _homeData.addSource(_comics) {
            updateComicsDataItem()
            checkAllDataSourcesUpdated()
        }
        _homeData.addSource(_series) {
            updateSeriesDataItem()
            checkAllDataSourcesUpdated()
        }

        _homeData.postValue(UIState.Loading)

        repository.getAllCharacters().applySchedulersAndPostUIStates(_characters::postValue)
        repository.getAllComics().applySchedulersAndPostUIStates(_comics::postValue)
        repository.getAllSeries().applySchedulersAndPostUIStates(_series::postValue)
    }

    private fun updateCharacterDataItem() {
        val characters = _characters.value?.toData()
        data.add(DataItem.HeaderItem(characters?.shuffled()?.take(4)!!))
        data.add(
            DataItem.CharacterTagItem(
                Tag("CHARACTERS", characters.shuffled()), this
            )
        )
    }

    private fun checkAllDataSourcesUpdated() {
        if (data.size == 4) {
            _homeData.postValue(UIState.Success(data.sortedBy { it.rank }))
        }
    }

    private fun updateComicsDataItem() {
        val comics = _comics.value?.toData() ?: emptyList()
        data.add(DataItem.ComicsTagItem(Tag("COMICS", comics.shuffled()), this))
    }

    private fun updateSeriesDataItem() {
        val series = _series.value?.toData() ?: emptyList()
        data.add(DataItem.SeriesTagItem(Tag("SERIES", series.shuffled()), this))
    }

    override fun onCleared() {
        super.onCleared()
        _homeData.removeSource(_characters)
        _homeData.removeSource(_comics)
        _homeData.removeSource(_series)
    }

//endregion



}
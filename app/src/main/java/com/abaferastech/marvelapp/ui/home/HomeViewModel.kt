package com.abaferastech.marvelapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.abaferastech.marvelapp.data.model.result.Characters
import com.abaferastech.marvelapp.data.model.result.Comics
import com.abaferastech.marvelapp.data.model.result.Series
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.ui.base.BaseViewModel
import com.abaferastech.marvelapp.ui.characters.CharactersInteractionListener
import com.abaferastech.marvelapp.ui.home.adapters.ComicsInteractionListener
import com.abaferastech.marvelapp.ui.home.adapters.SeriesInteractionListener
import com.abaferastech.marvelapp.ui.model.DataItem
import com.abaferastech.marvelapp.ui.model.Tag
import com.abaferastech.marvelapp.ui.model.UIState
import io.reactivex.rxjava3.disposables.CompositeDisposable

class HomeViewModel : BaseViewModel(), ComicsInteractionListener, CharactersInteractionListener,
    SeriesInteractionListener {
    private val repository = MarvelRepository()

    private val _homeData = MediatorLiveData<UIState<List<DataItem>>>()
    val homeData: MediatorLiveData<UIState<List<DataItem>>> get() = _homeData

    private val _characters = MutableLiveData<UIState<List<Characters>>>()
    private val _comics = MutableLiveData<UIState<List<Comics>>>()
    private val _series = MutableLiveData<UIState<List<Series>>>()


    private val _isCharacterClicked = MutableLiveData<Boolean>()
    val isCharacterClicked: LiveData<Boolean> get() = _isCharacterClicked

    private val _selectedCharacterID = MutableLiveData<Int>()
    val selectedCharacterID: LiveData<Int> get() = _selectedCharacterID


    val data = mutableListOf<DataItem>()

    init {
        _homeData.addSource(_characters) { updateCharacterDataItem() }
        _homeData.addSource(_comics) { updateComicsDataItem() }
        _homeData.addSource(_series) { updateSeriesDataItem() }

        _homeData.postValue(UIState.Loading)

        repository.getAllCharacters().applySchedulersAndPostUIStates(_characters::postValue)
        repository.getAllComics().applySchedulersAndPostUIStates(_comics::postValue)
        repository.getAllSeries().applySchedulersAndPostUIStates(_series::postValue)
    }

    private fun updateCharacterDataItem() {
        val characters = _characters.value?.toData()
        data.add(DataItem.HeaderItem(characters?.shuffled()?.take(3)!!))
        data.add(
            DataItem.CharacterTagItem(
                Tag("CHARACTERS", characters.shuffled()), this
            )
        )

        _homeData.postValue(UIState.Success(data.sortedBy { it.rank }))
    }

    private fun updateComicsDataItem() {
        val comics = _comics.value?.toData() ?: emptyList()
        data.add(DataItem.ComicsTagItem(Tag("COMICS", comics.shuffled()), this))

        _homeData.postValue(UIState.Success(data.sortedBy { it.rank }))
    }

    private fun updateSeriesDataItem() {
        val series = _series.value?.toData() ?: emptyList()
        data.add(DataItem.SeriesTagItem(Tag("SERIES", series.shuffled()), this))
        _homeData.postValue(UIState.Success(data.sortedBy { it.rank }))
    }

    override fun onCleared() {
        super.onCleared()
        _homeData.removeSource(_characters)
        _homeData.removeSource(_comics)
        _homeData.removeSource(_series)
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
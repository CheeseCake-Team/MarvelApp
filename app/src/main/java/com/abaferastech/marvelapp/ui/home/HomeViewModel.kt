package com.abaferastech.marvelapp.ui.home

import android.util.Log
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

class HomeViewModel : BaseViewModel(), CharactersInteractionListener,
    SeriesInteractionListener, ComicsInteractionListener {

    private val repository = MarvelRepository()

    private val _homeData = MediatorLiveData<List<DataItem>>()
    val homeData: MediatorLiveData<List<DataItem>> get() = _homeData

    private val _characters = MutableLiveData<UIState<List<Characters>>>()
    private val _comics = MutableLiveData<UIState<List<Comics>>>()
    private val _series = MutableLiveData<UIState<List<Series>>>()



    private val _selectedCharacterItem = MutableLiveData<SentData>()
    val selectedCharacterItem: LiveData<SentData> get() = _selectedCharacterItem


    private val _selectedComicItem = MutableLiveData<SentData>()
    val selectedComicItem: LiveData<SentData> get() = _selectedComicItem

    private val _selectedSeriesItem = MutableLiveData<SentData>()
    val selectedSeriesItem: LiveData<SentData> get() = _selectedSeriesItem






    init {
        _homeData.addSource(_characters) { updateHomeData() }
        _homeData.addSource(_comics) { updateHomeData() }
        _homeData.addSource(_series) { updateHomeData() }

        repository.getAllCharacters().applySchedulersAndPostUIStates(_characters::postValue)
        repository.getAllComics().applySchedulersAndPostUIStates(_comics::postValue)
        repository.getAllSeries().applySchedulersAndPostUIStates(_series::postValue)

    }

    override fun onCleared() {
        super.onCleared()
        _homeData.removeSource(_characters)
        _homeData.removeSource(_comics)
        _homeData.removeSource(_series)
    }

    private fun updateHomeData() {

        val characters = _characters.value?.toData() ?: emptyList()
        val comics = _comics.value?.toData() ?: emptyList()
        val series = _series.value?.toData() ?: emptyList()

        Log.d("TAG", "characters updateHomeData: $characters")
        Log.d("TAG", "comics updateHomeData: $comics")
        Log.d("TAG", "series updateHomeData: $series")

        val data = listOf(
            DataItem.HeaderItem(characters.shuffled().take(3)),
            DataItem.CharacterTagItem(
                Tag("CHARACTERS", characters.shuffled()), this),
            DataItem.ComicsTagItem(Tag("COMICS", comics.shuffled()),  this),
            DataItem.SeriesTagItem(Tag("SERIES", series.shuffled()),  this)
        )
        _homeData.postValue(data)
    }


    override fun onClickCharacter(character: Characters) {
        _selectedCharacterItem.postValue(SentData(true,character.id!!))
    }


    override fun onClickSeries(series: Series) {
        _selectedSeriesItem.postValue(SentData(true,series.id))
    }

    override fun onClickComics(comicId: Int) {
        _selectedComicItem.postValue(SentData(true,comicId))
    }

    fun resetCharacterDataSent() {
        _selectedCharacterItem.value?.clicked = false
    }

    fun resetComicDataSent(){
        _selectedComicItem.value?.clicked = false
    }


    data class SentData(var clicked: Boolean,val id: Int)
}


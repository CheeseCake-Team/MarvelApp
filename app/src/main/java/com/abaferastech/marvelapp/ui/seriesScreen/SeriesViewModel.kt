package com.abaferastech.marvelapp.ui.seriesScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.abaferastech.marvelapp.data.model.result.Series
import com.abaferastech.marvelapp.ui.model.UIState
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.ui.base.BaseViewModel

class SeriesViewModel : BaseViewModel() {
    private val repository = MarvelRepository()

    private val _series = MutableLiveData<UIState<List<Series>>>()
    val series: LiveData<UIState<List<Series>>> get() = _series

    init {
        getMarvelSeries()
    }
    private fun getMarvelSeries() {
        repository.getAllSeries()
            .applySchedulersAndPostUIStates(_series::postValue)
    }

    fun getComicSeries(characterId: Int) {
        repository.getComicSeries(characterId)
            .applySchedulersAndPostUIStates(_series::postValue)
    }

}

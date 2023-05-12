package com.abaferastech.marvelapp.ui.series.seriesViewAll

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.abaferastech.marvelapp.data.model.result.Series
import com.abaferastech.marvelapp.ui.model.UIState
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.ui.base.BaseViewModel

class SeriesViewAllViewModel : BaseViewModel () {
    private val repository = MarvelRepository()

    private val _seriesViewAll = MutableLiveData<UIState<List<Series>>>()
    val seriesViewAll: LiveData<UIState<List<Series>>> get() = _seriesViewAll

    init {
        getMarvelSeriesViewAll()
    }

    private fun getMarvelSeriesViewAll() {
        repository.getAllSeries()
            .applySchedulersAndPostUIStates(_seriesViewAll::postValue)
    }
}
package com.abaferastech.marvelapp.ui.series.seriesDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.abaferastech.marvelapp.data.model.result.Series
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.ui.base.BaseViewModel
import com.abaferastech.marvelapp.ui.model.UIState

class SeriesDetailsViewModel(state: SavedStateHandle) : BaseViewModel() {

    private val repository = MarvelRepository()
    private val _series = MutableLiveData<UIState<Series>>()
    val series: LiveData<UIState<Series>> get() = _series

    val seriesArgs = state.let {
        SeriesDetailsFragmentArgs.fromSavedStateHandle(it)
    }

    fun getSeriesById(passeId: Int? = null){
        val seriesId = passeId ?: seriesArgs.seriesId
        repository.getSingleSeries(seriesId)
            .applySchedulersAndPostUIStates(_series::postValue)
    }

}

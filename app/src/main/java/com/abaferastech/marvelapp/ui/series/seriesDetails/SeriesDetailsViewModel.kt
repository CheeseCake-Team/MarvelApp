package com.abaferastech.marvelapp.ui.series.seriesDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.abaferastech.marvelapp.data.domain.models.Series
import com.abaferastech.marvelapp.data.mapper.dtotodomain.ComicMapper
import com.abaferastech.marvelapp.data.mapper.dtotodomain.SeriesMapper
import com.abaferastech.marvelapp.data.remote.response.SeriesDTO
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.ui.base.BaseViewModel
import com.abaferastech.marvelapp.ui.model.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SeriesDetailsViewModel @Inject constructor(
    private val repository: MarvelRepository,
    savedStateHandle: SavedStateHandle,
) :
    BaseViewModel(savedStateHandle) {

    private val _series = MutableLiveData<Series>()
    val series: LiveData<Series> get() = _series

    init {
        val seriesId = getSavedStateValue<Int>("seriesId")
        seriesId?.let {
            getSingleSeriesById(it)
        }
    }


    var seriesId = MutableLiveData<Int>()

    fun saveSeriesId(seriesId: Int) {
        setSavedStateValue("seriesId", seriesId)
    }

    private fun getSingleSeriesById(passedId: Int) {
        repository.getSingleSeries(passedId).applySchedulersAndPostUIStates {
            _series.postValue(SeriesMapper().map(it.toData()!!))
        }
    }

}

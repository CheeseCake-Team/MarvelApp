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
import javax.inject.Inject

class SeriesDetailsViewModel @Inject constructor(private val repository: MarvelRepository) :
    BaseViewModel() {

    private val _series = MutableLiveData<Series>()
    val series: LiveData<Series> get() = _series


    var seriesId = MutableLiveData<Int>()

    fun getSeriesById(passeId: Int) {
        repository.getSingleSeries(passeId)
            .applySchedulersAndPostUIStates {
                _series.postValue(SeriesMapper().map(it.toData()!!))
            }
    }

}

package com.abaferastech.marvelapp.ui.series.seriesDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.abaferastech.marvelapp.data.remote.response.SeriesDTO
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.ui.base.BaseViewModel
import com.abaferastech.marvelapp.ui.model.UIState
import javax.inject.Inject

class SeriesDetailsViewModel
@Inject constructor(private val repository: MarvelRepository)
    : BaseViewModel() {

    private val _series = MutableLiveData<UIState<SeriesDTO>>()
    val series: LiveData<UIState<SeriesDTO>> get() = _series

    /*val seriesArgs = state.let {
        SeriesDetailsFragmentArgs.fromSavedStateHandle(it)
    }*/

    /*fun getSeriesById(passeId: Int? = null){
        val seriesId = passeId ?: seriesArgs.seriesId
        repository.getSingleSeries(seriesId)
            .applySchedulersAndPostUIStates(_series::postValue)
    }*/

}

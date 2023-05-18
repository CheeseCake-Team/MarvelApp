package com.abaferastech.marvelapp.ui.series.seriesDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.abaferastech.marvelapp.data.domain.models.Series
import com.abaferastech.marvelapp.data.mapper.dtotodomain.SeriesMapper
import com.abaferastech.marvelapp.data.remote.response.SeriesDTO
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.ui.base.BaseViewModel
import com.abaferastech.marvelapp.ui.model.UIState
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class SeriesDetailsViewModel
@Inject constructor(private val repository: MarvelRepository,
                    savedStateHandle: SavedStateHandle)
    : BaseViewModel(savedStateHandle) {

    private val _series = MutableLiveData<Series>()
    val series: LiveData<Series> = _series

    override val key: String
        get() = "seriesId"

    init {
        getSeriesById()
    }

    private fun getSeriesById(){
        fetchItem {
            repository.getSingleSeries(getPassedId()!!)
        }
    }

    private fun fetchItem(getItem: () -> Single<UIState<SeriesDTO>>) {
        getItem().applySchedulersAndPostUIStates {
            _series.postValue(SeriesMapper().map(it.toData()!!))
        }
    }

}

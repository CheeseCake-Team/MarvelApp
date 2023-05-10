package com.abaferastech.marvelapp.ui.seriesDetails

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.abaferastech.marvelapp.data.model.response.MarvelBaseResponse
import com.abaferastech.marvelapp.data.model.result.Comics
import com.abaferastech.marvelapp.data.model.result.Series
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.ui.base.BaseViewModel
import com.abaferastech.marvelapp.ui.model.UIState
import io.reactivex.rxjava3.kotlin.addTo

class SeriesDetailsViewModel : BaseViewModel() {

    private val repository = MarvelRepository()
    private val _series = MutableLiveData<UIState<Series>>()
    val series: LiveData<UIState<Series>> get() = _series

    fun getSeriesById(seriesId: Int){
        repository.getSingleSeries(seriesId)
            .applySchedulersAndPostUIStates(_series::postValue)
    }

}

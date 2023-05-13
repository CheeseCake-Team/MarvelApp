package com.abaferastech.marvelapp.ui.series.seriesScreen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.abaferastech.marvelapp.data.model.result.Series
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.ui.base.BaseViewModel
import com.abaferastech.marvelapp.ui.model.UIState
import io.reactivex.rxjava3.kotlin.addTo
import java.time.LocalDate

class SeriesViewModel : BaseViewModel() {
    private val repository = MarvelRepository()

    private val _series = MutableLiveData<UIState<List<Series>>>()
    val series: LiveData<UIState<List<Series>>> get() = _series

    init {
        getMarvelSeries()
    }
    fun getMarvelSeries() {
        repository.getAllSeries()
            .applySchedulersAndPostUIStates(_series::postValue)
    }

    fun getComicSeries(comicId: Int) {
        repository.getComicSeries(comicId)
            .applySchedulersAndPostUIStates(_series::postValue)
    }
    fun getEventSeries(eventId: Int) {
        repository.getEventSeries(eventId)
            .applySchedulersAndPostUIStates(_series::postValue)
    }

}

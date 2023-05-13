package com.abaferastech.marvelapp.ui.series.seriesViewAll

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.abaferastech.marvelapp.data.model.result.Series
import com.abaferastech.marvelapp.ui.model.UIState
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.ui.base.BaseViewModel
import com.abaferastech.marvelapp.ui.comic.comics.ComicEvents
import com.abaferastech.marvelapp.ui.model.Event

class SeriesViewAllViewModel : BaseViewModel (),SeriesViewAllInteractionListener {
    private val repository = MarvelRepository()

    private val _seriesViewAll = MutableLiveData<UIState<List<Series>>>()
    val seriesViewAll: LiveData<UIState<List<Series>>> get() = _seriesViewAll

    val navigationEvents = MutableLiveData<Event<SeriesEvents>>()


    init {
        getMarvelSeriesViewAll()
    }

    private fun getMarvelSeriesViewAll() {
        repository.getAllSeries()
            .applySchedulersAndPostUIStates(_seriesViewAll::postValue)
    }

    override fun onClickSeries(series: Series) {
        navigationEvents.postValue(Event(SeriesEvents.ClickSeriesEvent(series.id)))
    }
    fun getMarvelSeries() {
        repository.getAllSeries()
            .applySchedulersAndPostUIStates(_seriesViewAll::postValue)
    }

    fun getComicSeries(comicId: Int) {
        repository.getComicSeries(comicId)
            .applySchedulersAndPostUIStates(_seriesViewAll::postValue)
    }
    fun getEventSeries(eventId: Int) {
        repository.getEventSeries(eventId)
            .applySchedulersAndPostUIStates(_seriesViewAll::postValue)
    }
}
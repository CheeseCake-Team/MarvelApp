package com.abaferastech.marvelapp.ui.series.seriesViewAll

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.abaferastech.marvelapp.data.remote.response.SeriesDTO
import com.abaferastech.marvelapp.ui.model.UIState
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.ui.base.BaseViewModel
import com.abaferastech.marvelapp.ui.model.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SeriesViewAllViewModel @Inject constructor(private val repository: MarvelRepository) : BaseViewModel (),SeriesViewAllInteractionListener {


    private val _seriesViewAll = MutableLiveData<UIState<List<SeriesDTO>>>()
    val seriesViewAll: LiveData<UIState<List<SeriesDTO>>> get() = _seriesViewAll

    val navigationEvents = MutableLiveData<Event<SeriesEvents>>()


    init {
        getMarvelSeriesViewAll()
    }

    private fun getMarvelSeriesViewAll() {
        repository.getAllSeries()
            .applySchedulersAndPostUIStates(_seriesViewAll::postValue)
    }

    override fun onClickSeries(series: SeriesDTO) {
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
    fun getCreatorSeries(creatorId: Int) {
        repository.getCreatorSeries(creatorId)
            .applySchedulersAndPostUIStates(_seriesViewAll::postValue)
    }
}
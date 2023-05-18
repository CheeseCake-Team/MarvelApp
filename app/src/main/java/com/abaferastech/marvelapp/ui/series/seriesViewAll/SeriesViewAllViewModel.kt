package com.abaferastech.marvelapp.ui.series.seriesViewAll

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.domain.models.Series
import com.abaferastech.marvelapp.ui.base.BaseViewModel
import com.abaferastech.marvelapp.ui.model.EventModel
import com.abaferastech.marvelapp.ui.model.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel

class SeriesViewAllViewModel @Inject constructor(val repository: MarvelRepository) :
    BaseViewModel(), SeriesViewAllInteractionListener {

    private val _seriesViewAll = MutableLiveData<UIState<List<Series>>>()
    val seriesViewAll: LiveData<UIState<List<Series>>> get() = _seriesViewAll

    val navigationEvents = MutableLiveData<EventModel<SeriesEvents>>()


    init {
        getMarvelSeriesViewAll()
    }

    private fun getMarvelSeriesViewAll() {
        repository.getAllSeries().applySchedulersAndPostUIStates(_seriesViewAll::postValue)
    }

    override fun onClickSeries(series: Series) {
        navigationEvents.postValue(EventModel(SeriesEvents.ClickSeriesEvent(series.id)))
    }

    fun getMarvelSeries() {
        repository.getAllSeries().applySchedulersAndPostUIStates(_seriesViewAll::postValue)
    }

    fun getComicSeries(comicId: Int) {
        repository.getComicSeries(comicId).applySchedulersAndPostUIStates(_seriesViewAll::postValue)
    }

    fun getEventSeries(eventId: Int) {
        repository.getEventSeries(eventId).applySchedulersAndPostUIStates(_seriesViewAll::postValue)
    }

    fun getCreatorSeries(creatorId: Int) {
        repository.getCreatorSeries(creatorId)
            .applySchedulersAndPostUIStates(_seriesViewAll::postValue)
    }
}
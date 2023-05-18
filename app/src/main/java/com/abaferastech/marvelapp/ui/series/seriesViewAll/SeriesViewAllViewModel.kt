package com.abaferastech.marvelapp.ui.series.seriesViewAll

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.abaferastech.marvelapp.data.domain.models.Series
import com.abaferastech.marvelapp.data.mapper.dtotodomain.SeriesMapper
import com.abaferastech.marvelapp.data.remote.response.SeriesDTO
import com.abaferastech.marvelapp.ui.model.UIState
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.ui.base.BaseViewModel
import com.abaferastech.marvelapp.ui.model.EventHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

@HiltViewModel
class SeriesViewAllViewModel @Inject constructor(
    private val repository: MarvelRepository,
    savedStateHandle: SavedStateHandle
) : BaseViewModel(savedStateHandle), SeriesViewAllInteractionListener {


    private val _seriesViewAll = MutableLiveData<List<Series>>()
    val seriesViewAll: LiveData<List<Series>> get() = _seriesViewAll

    val navigationEvents = MutableLiveData<EventHandler<SeriesEvents>>()

    override val key: String
        get() = "seriesViewAllId"

    init {
        getMarvelSeries()
    }


    private fun getMarvelSeries() {
        fetchItemsList {
            repository.getAllSeries()
        }
    }

    fun getComicSeries() {
        fetchItemsList {
            repository.getComicSeries(getPassedId()!!)
        }
    }

    fun getEventSeries() {
        fetchItemsList {
            repository.getEventSeries(getPassedId()!!)
        }
    }

    fun getCreatorSeries() {
        fetchItemsList {
            repository.getCreatorSeries(getPassedId()!!)
        }
    }

    override fun onClickSeries(series: Series) {
        navigationEvents.postValue(EventHandler(SeriesEvents.ClickSeriesEvent(series.id)))
    }

    private fun fetchItemsList(getItemsList: () -> Single<UIState<List<SeriesDTO>>>) {
        getItemsList()
            .applySchedulersAndPostUIStates { dtoList ->
                _seriesViewAll.postValue(convertDtoToListDomain(dtoList.toData()!!))
            }
    }
    private fun convertDtoToListDomain(list: List<SeriesDTO>): MutableList<Series> {
        val result = mutableListOf<Series>()
        list.forEach {
            result.add(SeriesMapper().map(it))
        }
        return result
    }
}
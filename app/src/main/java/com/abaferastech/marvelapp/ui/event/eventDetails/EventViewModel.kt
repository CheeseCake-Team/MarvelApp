package com.abaferastech.marvelapp.ui.event.eventDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.abaferastech.marvelapp.data.domain.models.Comic
import com.abaferastech.marvelapp.data.domain.models.Event
import com.abaferastech.marvelapp.data.mapper.dtotodomain.ComicMapper
import com.abaferastech.marvelapp.data.mapper.dtotodomain.EventMapper
import com.abaferastech.marvelapp.data.remote.response.ComicDTO
import com.abaferastech.marvelapp.data.remote.response.EventDTO
import com.abaferastech.marvelapp.ui.model.UIState
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

@HiltViewModel
class EventViewModel @Inject constructor(private val repository: MarvelRepository,
                                         savedStateHandle: SavedStateHandle) : BaseViewModel(savedStateHandle) {

    /*val eventArgs = state.let {
        EventFragmentArgs.fromSavedStateHandle(it)
    }*/

    private val _event = MutableLiveData<Event>()
    val event: LiveData<Event> = _event
    override val key: String
        get() = "eventDetailsId"

    init {
        getSingleEventById()
    }

    /*fun getSingleEvent(eventsId:Int? = null) {
        val eventId = eventsId ?: eventArgs.eventId
        repository.getSingleEvent(eventId)
            .applySchedulersAndPostUIStates(_event::postValue)

    }*/

    private fun getSingleEventById() {
        fetchItem {
            repository.getSingleEvent(getPassedId()!!)
        }
    }

    private fun fetchItem(getItem: () -> Single<UIState<EventDTO>>) {
        getItem().applySchedulersAndPostUIStates {
                _event.postValue(EventMapper().map(it.toData()!!))
            }
    }

}
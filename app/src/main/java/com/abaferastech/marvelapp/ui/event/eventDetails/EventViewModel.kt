package com.abaferastech.marvelapp.ui.event.eventDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.domain.models.Event
import com.abaferastech.marvelapp.ui.base.BaseViewModel
import com.abaferastech.marvelapp.ui.model.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel

class EventViewModel @Inject constructor(
    val repository: MarvelRepository,
    state: SavedStateHandle
) : BaseViewModel() {

    val eventArgs = state.let {
        EventFragmentArgs.fromSavedStateHandle(it)
    }

    private val _event = MutableLiveData<UIState<Event>>()
    val event: LiveData<UIState<Event>> get() = _event

    fun getSingleEvent(eventsId: Int? = null) {
        val eventId = eventsId ?: eventArgs.eventId
        repository.getSingleEvent(eventId)
            .applySchedulersAndPostUIStates(_event::postValue)

    }
    fun refresh() {
        val eventId =  eventArgs.eventId
        getSingleEvent(eventId)
    }
}
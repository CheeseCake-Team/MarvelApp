package com.abaferastech.marvelapp.ui.events

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.abaferastech.marvelapp.data.model.result.Events
import com.abaferastech.marvelapp.ui.model.UIState
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.ui.base.BaseViewModel

class EventsViewModel : BaseViewModel() {
    private val repository = MarvelRepository()

    private val _events = MutableLiveData<UIState<List<Events>>>()
    val events: LiveData<UIState<List<Events>>> get() = _events
    
    private val _characterEvent = MutableLiveData<UIState<List<Events>>>()
    val characterEvent: LiveData<UIState<List<Events>>> get() = _characterEvent


    fun getMarvelEvents() {
        repository.getAllEvents()
            .applySchedulersAndSubscribe(_events::postValue)
    }

    fun getEventsById(eventsId: Int) {
        repository.getCharacterEvents(eventsId)
            .applySchedulersAndSubscribe(_characterEvent::postValue)
    }

}
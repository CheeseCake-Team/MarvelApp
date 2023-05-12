package com.abaferastech.marvelapp.ui.event.events

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.abaferastech.marvelapp.data.model.result.Events
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.ui.base.BaseViewModel
import com.abaferastech.marvelapp.ui.event.events.EventsInteractionListener
import com.abaferastech.marvelapp.ui.model.Event
import com.abaferastech.marvelapp.ui.model.UIState

class EventsViewModel : BaseViewModel() , EventsInteractionListener {
    private val repository = MarvelRepository()
    val navigationEvents = MutableLiveData<Event<EvenEvents>>()

    private val _events = MutableLiveData<UIState<List<Events>>>()
    val events: LiveData<UIState<List<Events>>> get() = _events

//    private val _characterEvents = MutableLiveData<UIState<List<Events>>>()
//    private val _comicEvents = MutableLiveData<UIState<List<Events>>>()
//    private val _seriesEvents = MutableLiveData<UIState<List<Events>>>()


    fun getMarvelEvents() {
        repository.getAllEvents()
            .applySchedulersAndPostUIStates(_events::postValue)
    }

    fun getCharacterEvents(characterId: Int) {
        repository.getCharacterEvents(characterId)
            .applySchedulersAndPostUIStates(_events::postValue)
    }

    fun getComicEvents(comicsId: Int) {
        repository.getComicEvents(comicsId)
            .applySchedulersAndPostUIStates(_events::postValue)
    }

    override fun onEventClick(event: Events) {
        navigationEvents.postValue(Event(EvenEvents.ClickEventEvents(event.id!!)))
    }
}
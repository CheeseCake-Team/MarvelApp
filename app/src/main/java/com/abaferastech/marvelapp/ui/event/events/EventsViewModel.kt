package com.abaferastech.marvelapp.ui.event.events

import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.abaferastech.marvelapp.data.remote.response.EventDTO
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.ui.base.BaseViewModel
import com.abaferastech.marvelapp.ui.model.Event
import com.abaferastech.marvelapp.ui.model.UIState

class EventsViewModel() : BaseViewModel(), EventsInteractionListener {
    private val repository by lazy { MarvelRepository() }

    val navigationEvents = MutableLiveData<Event<EvenEvents>>()

    private val _events = MutableLiveData<UIState<List<EventDTO>>>()
    val events: LiveData<UIState<List<EventDTO>>> get() = _events

    fun getMarvelEvents() {
        repository.getAllEvents()
            .applySchedulersAndPostUIStates(_events::postValue)
    }

    fun getCharacterEvents(characterId: Int) {
        repository.getCharacterEvents(characterId)
            .applySchedulersAndPostUIStates(_events::postValue)
    }

    fun getCreatorEvents(creatorId: Int) {
        repository.getCreatorEvents(creatorId)
            .applySchedulersAndPostUIStates(_events::postValue)
    }

    fun getComicEvents(comicsId: Int) {
        repository.getComicEvents(comicsId)
            .applySchedulersAndPostUIStates(_events::postValue)
    }

    override fun onEventClick(event: EventDTO) {
        navigationEvents.postValue(Event(EvenEvents.ClickEventEvents(event.id!!)))
    }
    private lateinit var state: Parcelable
    fun saveRecyclerViewState(parcelable: Parcelable) {
        state = parcelable
    }
    fun restoreRecyclerViewState(): Parcelable = state
    fun stateInitialized(): Boolean = ::state.isInitialized

}
package com.abaferastech.marvelapp.ui.event.events

import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.abaferastech.marvelapp.data.remote.response.EventDTO
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.ui.base.BaseViewModel
import com.abaferastech.marvelapp.ui.model.EventModel
import com.abaferastech.marvelapp.domain.models.Event
import com.abaferastech.marvelapp.ui.model.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel

class EventsViewModel @Inject constructor(val repository:MarvelRepository) : BaseViewModel(), EventsInteractionListener {

    val navigationEvents = MutableLiveData<EventModel<EvenEvents>>()

    private val _events = MutableLiveData<UIState<List<Event>>>()
    val events: LiveData<UIState<List<Event>>> get() = _events

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
        navigationEvents.postValue(EventModel(EvenEvents.ClickEventEvents(event.id!!)))
    }
    private lateinit var state: Parcelable
    fun saveRecyclerViewState(parcelable: Parcelable) {
        state = parcelable
    }
    fun restoreRecyclerViewState(): Parcelable = state
    fun stateInitialized(): Boolean = ::state.isInitialized

}
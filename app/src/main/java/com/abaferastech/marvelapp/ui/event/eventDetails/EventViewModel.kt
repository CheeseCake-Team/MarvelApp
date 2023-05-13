package com.abaferastech.marvelapp.ui.event.eventDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.abaferastech.marvelapp.data.model.result.Comics
import com.abaferastech.marvelapp.data.model.result.Events
import com.abaferastech.marvelapp.ui.model.UIState
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.ui.base.BaseViewModel
import com.abaferastech.marvelapp.ui.character.characterDetails.CharacterDetailsFragmentArgs

class EventViewModel(state: SavedStateHandle) : BaseViewModel() {
    private val repository by lazy { MarvelRepository() }

    val eventArgs = state.let {
        EventFragmentArgs.fromSavedStateHandle(it)
    }

    private val _event = MutableLiveData<UIState<Events>>()
    val event: LiveData<UIState<Events>> get() = _event

    fun getSingleEvent(eventsId:Int? = null) {
        val eventId = eventsId ?: eventArgs.eventId
        repository.getSingleEvent(eventId)
            .applySchedulersAndPostUIStates(_event::postValue)

    }

}
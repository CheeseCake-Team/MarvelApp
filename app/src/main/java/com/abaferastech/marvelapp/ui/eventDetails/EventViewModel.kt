package com.abaferastech.marvelapp.ui.eventDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.abaferastech.marvelapp.data.model.result.Comics
import com.abaferastech.marvelapp.data.model.result.Events
import com.abaferastech.marvelapp.ui.model.UIState
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.ui.base.BaseViewModel

class EventViewModel : BaseViewModel() {
    private val repository = MarvelRepository()

    private val _comics = MutableLiveData<UIState<List<Comics>>>()
    val comics: LiveData<UIState<List<Comics>>> get() = _comics

    private val _event = MutableLiveData<UIState<Events>>()
    val event: LiveData<UIState<Events>> get() = _event


    fun getEventComics(eventId: Int) {
        repository.getEventComics(eventId)
            .applySchedulersAndPostUIStates(_comics::postValue)
    }

    fun getSingleEvent(eventsId:Int) {
        repository.getSingleEvent(eventsId)
            .applySchedulersAndPostUIStates(_event::postValue)

    }

}
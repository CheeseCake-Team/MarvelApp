package com.abaferastech.marvelapp.ui.eventDetailsScreen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.abaferastech.marvelapp.data.model.result.Comics
import com.abaferastech.marvelapp.data.model.result.Events
import com.abaferastech.marvelapp.data.model.response.MarvelBaseResponse
import com.abaferastech.marvelapp.data.model.uimodel.UIState
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.ui.base.BaseViewModel
import io.reactivex.rxjava3.kotlin.addTo

class EventViewModel : BaseViewModel() {
    private val repository = MarvelRepository()

    private val _comics = MutableLiveData<UIState<List<Comics>>>()
    val comics: LiveData<UIState<List<Comics>>> get() = _comics

    private val _event = MutableLiveData<UIState<Events>>()
    val event: LiveData<UIState<Events>> get() = _event


    fun getEventComics(eventId: Int) {
        repository.getEventComics(eventId)
            .applySchedulersAndSubscribe(_comics::postValue,::onComicsError)
    }

    fun getSingleEvent(eventsId:Int) {
        repository.getSingleEvent(eventsId)
            .applySchedulersAndSubscribe(_event::postValue,::onEventError)

    }

    private fun onEventError(errorMessage: Throwable) {
        _event.postValue(UIState.Error(errorMessage.message.toString()))
    }
    private fun onComicsError(errorMessage: Throwable) {
        _comics.postValue(UIState.Error(errorMessage.message.toString()))
    }

}
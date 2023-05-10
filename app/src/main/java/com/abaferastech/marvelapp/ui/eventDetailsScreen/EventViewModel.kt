package com.abaferastech.marvelapp.ui.eventDetailsScreen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.abaferastech.marvelapp.data.model.Comics
import com.abaferastech.marvelapp.data.model.Events
import com.abaferastech.marvelapp.data.model.response.MarvelResponse
import com.abaferastech.marvelapp.data.model.state.State
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.ui.base.BaseViewModel
import io.reactivex.rxjava3.kotlin.addTo

class EventViewModel : BaseViewModel() {
    private val repository = MarvelRepository()
    private val _comics = MutableLiveData<List<Comics>>()
    val comics: LiveData<List<Comics>> get() = _comics
    private val _event = MutableLiveData<Events>()
    val event: LiveData<Events> get() = _event


    fun getEventComics(eventId: Int) {
        repository.getEventComics(eventId)
            .subscribe(::onSuccess, ::onError)
            .addTo(compositeDisposable)
    }

    fun getEventsById(eventsId:Int) {
        repository.getEventsById(eventsId)
            .subscribe(::onEventSuccess, ::onError)
            .addTo(compositeDisposable)
    }

    private fun onEventSuccess(state: State<MarvelResponse<Events>>) {
        when (state) {
            is State.Error -> TODO()
            State.Loading -> TODO()
            is State.Success -> {
                _event.postValue(state.toData()?.data?.results?.first())
            }
        }
    }

    private fun onSuccess(state: State<MarvelResponse<Comics>>) {

        when (state) {
            is State.Error -> TODO()
            State.Loading -> TODO()
            is State.Success -> {
                _comics.postValue(state.toData()?.data?.results)
            }
        }
    }

    private fun onError(e: Throwable) {
        Log.e("MarvelAPI", "getEventComics() - Error: ${e.message}")
    }
}
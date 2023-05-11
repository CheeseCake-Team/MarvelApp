package com.abaferastech.marvelapp.ui.eventScreen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.abaferastech.marvelapp.data.model.Characters
import com.abaferastech.marvelapp.data.model.Creators
import com.abaferastech.marvelapp.data.model.Events
import com.abaferastech.marvelapp.data.model.response.MarvelResponse
import com.abaferastech.marvelapp.data.model.state.State
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.ui.base.BaseViewModel
import io.reactivex.rxjava3.kotlin.addTo

class EventsViewModel : BaseViewModel() {
    private val repository = MarvelRepository()
    private val _characters = MutableLiveData<List<Characters>>()
    private val _creators  = MutableLiveData<List<Creators>>()

    private val _events = MutableLiveData<List<Events>>()
    val characters: LiveData<List<Characters>> get() = _characters
    val creators: LiveData<List<Creators>> get() = _creators
    val events: LiveData<List<Events>> get() = _events

    fun getMarvelEvents() {
        repository.getAllEvents()
            .subscribe(::onSuccess, ::onError)
            .addTo(compositeDisposable)
    }

    fun getEventsById(eventsId:Int) {
        repository.getCharacterEvents(eventsId)
            .subscribe(::onSuccess, ::onError)
            .addTo(compositeDisposable)
    }

    private fun onSuccess(state: State<MarvelResponse<Events>>) {
        when (state) {
            is State.Error -> Log.i("wEVevWVWVFWEewfFEWWfFFea", "error: ")
            State.Loading -> TODO()
            is State.Success -> {
                Log.i("Ekko",state.toData()?.data?.results.toString())
                _events.postValue(state.toData()?.data?.results)
            }
        }
    }

    private fun onError(e: Throwable) {
        Log.e("MarvelAPI", "getMarvelEvents() - Error: ${e.message}")
    }

    fun getEventCharacters(eventId: Int) {
        repository.getEventCharacters(eventId)
            .subscribe(::onSuccessCharacters, ::onError)
            .addTo(compositeDisposable)
    }
    fun getEventCreators(eventId: Int) {
        repository.getEventCreators(eventId)
            .subscribe(::onSuccessCreators, ::onError)
            .addTo(compositeDisposable)
    }
    private fun onSuccessCharacters(state: State<MarvelResponse<Characters>>) {

        when (state) {
            is State.Error -> TODO()
            State.Loading -> TODO()
            is State.Success -> {
                _characters.postValue(state.toData()?.data?.results)
            }
        }
    }
    private fun onSuccessCreators(state: State<MarvelResponse<Creators>>) {

        when (state) {
            is State.Error -> TODO()
            State.Loading -> TODO()
            is State.Success -> {
                _creators.postValue(state.toData()?.data?.results)
            }
        }
    }

}
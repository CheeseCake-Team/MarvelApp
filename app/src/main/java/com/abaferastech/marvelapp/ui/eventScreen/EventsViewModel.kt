package com.abaferastech.marvelapp.ui.eventScreen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.abaferastech.marvelapp.data.model.result.Events
import com.abaferastech.marvelapp.data.model.response.MarvelBaseResponse
import com.abaferastech.marvelapp.data.model.uimodel.UIState
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.ui.base.BaseViewModel
import io.reactivex.rxjava3.kotlin.addTo

class EventsViewModel : BaseViewModel() {
    private val repository = MarvelRepository()

    private val _events = MutableLiveData<List<Events>>()
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

    private fun onSuccess(state: UIState<MarvelBaseResponse<Events>>) {
        when (state) {
            is UIState.Error -> Log.i("wEVevWVWVFWEewfFEWWfFFea", "error: ")
            UIState.Loading -> TODO()
            is UIState.Success -> {
                Log.i("Ekko",state.toData()?.data?.results.toString())
                _events.postValue(state.toData()?.data?.results)
            }
        }
    }

    private fun onError(e: Throwable) {
        Log.e("MarvelAPI", "getMarvelEvents() - Error: ${e.message}")
    }
}
package com.abaferastech.marvelapp.ui.eventScreen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.abaferastech.marvelapp.data.model.Events
import com.abaferastech.marvelapp.data.model.response.MarvelResponse
import com.abaferastech.marvelapp.data.model.state.State
import com.abaferastech.marvelapp.ui.base.BaseViewModel
import io.reactivex.rxjava3.kotlin.addTo

class EventsViewModel : BaseViewModel() {

    private val _events = MutableLiveData<List<Events>>()
    val events: LiveData<List<Events>> get() = _events

    init {
        getMarvelEvents()
    }

    private fun getMarvelEvents() {
        repository.getAllEvents()
            .subscribe(::onSuccess, ::onError)
            .addTo(compositeDisposable)
    }

    private fun onSuccess(state: State<MarvelResponse<Events>>) {
        when (state) {
            is State.Error -> TODO()
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
}
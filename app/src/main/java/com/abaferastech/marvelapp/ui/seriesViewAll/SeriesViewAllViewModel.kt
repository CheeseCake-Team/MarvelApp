package com.abaferastech.marvelapp.ui.seriesViewAll

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.abaferastech.marvelapp.data.model.Series
import com.abaferastech.marvelapp.data.model.response.MarvelResponse
import com.abaferastech.marvelapp.data.model.state.State
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.ui.base.BaseViewModel

import io.reactivex.rxjava3.kotlin.addTo

class SeriesViewAllViewModel : BaseViewModel () {
    private val repository = MarvelRepository()
    private val _seriesviewall = MutableLiveData<List<Series>>()
    val seriesviewall: LiveData<List<Series>> get() = _seriesviewall

    init {
        getMarvelSeriesViewAll()
    }

    private fun getMarvelSeriesViewAll() {
        repository.getAllSeries()
            .subscribe(::onSuccess, ::OnError)
            .addTo(compositeDisposable)
    }

    private fun onSuccess(state: State<MarvelResponse<Series>>) {
        when (state) {
            is State.Error -> TODO()
            State.Loading -> TODO()
            is State.Success -> {
                state.toData()?.data?.results.toString()
                _seriesviewall.postValue(state.toData()?.data?.results)
            }
        }

    }


}

private fun OnError(e: Throwable) {
    Log.e("MarvelAPI", "getMarvelEvents() - Error: ${e.message}")

}


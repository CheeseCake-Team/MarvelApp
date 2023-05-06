package com.abaferastech.marvelapp.ui.seriesScreen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.abaferastech.marvelapp.data.model.Series
import com.abaferastech.marvelapp.data.model.response.MarvelResponse
import com.abaferastech.marvelapp.data.model.state.State
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.ui.base.BaseViewModel
import io.reactivex.rxjava3.kotlin.addTo
import java.time.LocalDate

class SeriesViewModel : BaseViewModel() {
    private val repository = MarvelRepository()
    private val _series = MutableLiveData<List<Series>>()
    val series: LiveData<List<Series>> get() = _series

    init {
        getMarvelSeries()
    }

    private fun getMarvelSeries() {
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
                _series.postValue(state.toData()?.data?.results)
            }
        }

    }


    }


private fun OnError(e: Throwable) {
    Log.e("MarvelAPI", "getMarvelEvents() - Error: ${e.message}")

}

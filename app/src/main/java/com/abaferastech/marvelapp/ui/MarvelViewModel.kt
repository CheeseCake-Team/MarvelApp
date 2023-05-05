package com.abaferastech.marvelapp.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.abaferastech.marvelapp.data.model.Series
import com.abaferastech.marvelapp.data.model.response.MarvelResponse
import com.abaferastech.marvelapp.data.model.state.State
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.ui.base.BaseViewModel
import io.reactivex.rxjava3.kotlin.addTo

class MarvelViewModel : BaseViewModel() {

    private val repository = MarvelRepository()


    private val _series = MutableLiveData<Series>()
    val series: LiveData<Series> get() = _series


    init {
        getMarvelStories()
    }

    private fun getMarvelStories() {
        repository.getSeriesFullUrl("http://gateway.marvel.com/v1/public/series/757")
            .subscribe(::onSuccess, ::onError)
            .addTo(compositeDisposable)

    }

    private fun onSuccess(state: State<MarvelResponse<Series>>) {
        when (state) {
            is State.Error -> TODO()
            State.Loading -> TODO()
            is State.Success -> {
                _series.postValue(state.toData()?.data?.results?.first())
            }
        }
    }

    private fun onError(e: Throwable) {
        Log.e("MarvelAPI", "getMarvelStories() - Error: ${e.message}")
    }
}

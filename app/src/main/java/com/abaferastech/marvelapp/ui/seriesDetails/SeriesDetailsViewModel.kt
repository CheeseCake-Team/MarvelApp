package com.abaferastech.marvelapp.ui.seriesDetails

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.abaferastech.marvelapp.data.model.Series
import com.abaferastech.marvelapp.data.model.response.MarvelResponse
import com.abaferastech.marvelapp.data.model.state.State
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.ui.base.BaseViewModel
import io.reactivex.rxjava3.kotlin.addTo

class SeriesDetailsViewModel : BaseViewModel() {

    private val repository = MarvelRepository()
    private val _series = MutableLiveData<Series>()
    val series: LiveData<Series> get() = _series


    fun getSeriesById(seriesId: Int) {
        repository.getSingleSeries(seriesId)
            .subscribe(::onSuccess, ::onError)
            .addTo(compositeDisposable)
    }

    private fun onSuccess(state: State<MarvelResponse<Series>>) {
        when (state) {
            is State.Error -> TODO()
            State.Loading -> TODO()
            is State.Success -> {
                state.toData()?.data?.results.toString()
                _series.postValue(state.toData()?.data?.results?.first())
            }
        }
    }

    private fun onError(e: Throwable) {
        Log.e("MarvelAPI", "getMarvelCharacters() - Error: ${e.message}")
    }
}

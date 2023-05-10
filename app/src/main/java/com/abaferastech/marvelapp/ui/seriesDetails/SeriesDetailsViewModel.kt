package com.abaferastech.marvelapp.ui.seriesDetails

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.abaferastech.marvelapp.data.model.response.MarvelBaseResponse
import com.abaferastech.marvelapp.data.model.result.Series
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.ui.base.BaseViewModel
import com.abaferastech.marvelapp.ui.model.UIState
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

    private fun onSuccess(state: UIState<MarvelBaseResponse<Series>>) {
        when (state) {
            is UIState.Error -> TODO()
            UIState.Loading -> TODO()
            is UIState.Success -> {
                state.toData()?.data?.results.toString()
                _series.postValue(state.toData()?.data?.results?.first())
            }
        }
    }

    private fun onError(e: Throwable) {
        Log.e("MarvelAPI", "getMarvelCharacters() - Error: ${e.message}")
    }
}

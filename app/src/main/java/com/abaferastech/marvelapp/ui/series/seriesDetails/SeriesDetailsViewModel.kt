package com.abaferastech.marvelapp.ui.series.seriesDetails

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.abaferastech.marvelapp.data.remote.response.SeriesDTO
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.domain.models.Series
import com.abaferastech.marvelapp.ui.base.BaseViewModel
import com.abaferastech.marvelapp.ui.model.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel

class SeriesDetailsViewModel @Inject constructor(
    private val repository: MarvelRepository,
    state: SavedStateHandle
) : BaseViewModel() {

    private val _series = MutableLiveData<UIState<Series>>()
    val series: LiveData<UIState<Series>> get() = _series

    val seriesArgs = state.let {
        SeriesDetailsFragmentArgs.fromSavedStateHandle(it)
    }

    var isFavouriteClicked = MutableLiveData<Boolean>()

    private val _isSeriesFavourite = MutableLiveData(false)
    val isSeriesFavourite: LiveData<Boolean> get() = _isSeriesFavourite


    fun getSeriesById(passeId: Int? = null) {
        val seriesId = passeId ?: seriesArgs.seriesId
        Completable.fromAction {
            repository.getSingleSeries(seriesId).doOnSuccess {
                Log.i("it",it.toString())
                when (it) {
                    is UIState.Success -> {
                        _isSeriesFavourite.postValue(it.toData()?.isFavourite)
                    }

                    else -> {
                        _isSeriesFavourite.postValue(false)
                    }
                }
            }
                .applySchedulersAndPostUIStates(_series::postValue)
        }
            .subscribeOn(Schedulers.io())
            .subscribe()
            .addTo(compositeDisposable)
    }

    fun refresh() {
        val seriesId = seriesArgs.seriesId
        getSeriesById(seriesId)
    }

    fun insertSeries() {
        Completable.fromAction {
            _series.value?.toData()?.apply {

                isFavourite = true
                Log.i("fadsf", this.toString())   }
                ?.let { repository.insertSeries(it) }
        }
            .subscribeOn(Schedulers.io())
            .subscribe()
            .addTo(compositeDisposable)
    }

    fun deleteSeries() {
        Completable.fromAction {
            _series.value?.toData()?.let { repository.deleteSeries(it) }
        }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe()
            .addTo(compositeDisposable)
    }

    fun onFavouriteClick() {

    }

}

package com.abaferastech.marvelapp.ui.seriesDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.abaferastech.marvelapp.data.model.Events
import com.abaferastech.marvelapp.data.model.Series
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.ui.base.BaseViewModel
import io.reactivex.rxjava3.internal.jdk8.FlowableFlatMapStream.subscribe
import io.reactivex.rxjava3.kotlin.addTo

class SeriesViewModel : BaseViewModel() {

    private val repository = MarvelRepository()

    private val _seriesDetails = MutableLiveData<List<Series>>()
    val events: LiveData<List<Series>> get() = _seriesDetails



    fun getSeriesById(seriesId:Int) {
//        repository.getSeriesCharacters(seriesId)
//            .subscribe(::onSuccess, ::onError)
//            .addTo(compositeDisposable)
    }
}
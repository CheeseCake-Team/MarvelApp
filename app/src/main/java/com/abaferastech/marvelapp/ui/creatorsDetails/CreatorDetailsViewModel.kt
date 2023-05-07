package com.abaferastech.marvelapp.ui.creatorsDetails

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.abaferastech.marvelapp.data.model.Creators
import com.abaferastech.marvelapp.data.model.Series
import com.abaferastech.marvelapp.data.model.response.MarvelResponse
import com.abaferastech.marvelapp.data.model.state.State
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.ui.base.BaseViewModel
import io.reactivex.rxjava3.kotlin.addTo

class CreatorDetailsViewModel : BaseViewModel() {
    private val repository by lazy { MarvelRepository() }

    private val _creator = MutableLiveData<Creators>()
    val creator: LiveData<Creators> get() = _creator

    private val _series = MutableLiveData<List<Series>>()
    val series: LiveData<List<Series>> get() = _series


    fun getMarvelCreator(id: Int) {
        repository.getSingleCreator(id)
            .subscribe(::onSuccessCreator, ::onError)
            .addTo(compositeDisposable)

        repository.getCreatorSeries(id)
            .subscribe(::onSuccessSeries)
            .addTo(compositeDisposable)

    }

    private fun onSuccessCreator(state: State<MarvelResponse<Creators>>) {
        when (state) {
            is State.Error -> TODO()
            State.Loading -> TODO()
            is State.Success -> {
                _creator.postValue(state.toData()?.data?.results?.first())
            }
        }
    }

    private fun onError(e: Throwable) {
        Log.e("MarvelAPI", "getMarvelCreatorSeries() - Error: ${e.message}")
    }

    fun onClickCreator(creator: Creators) {
        TODO("Not yet implemented")
    }

    private fun onSuccessSeries(state: State<MarvelResponse<Series>>) {
        when (state) {
            is State.Error -> TODO()
            State.Loading -> TODO()
            is State.Success -> {
                _series.postValue(state.toData()?.data?.results)

            }
        }
    }
}









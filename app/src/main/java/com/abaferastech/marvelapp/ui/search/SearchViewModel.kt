package com.abaferastech.marvelapp.ui.search

import android.text.TextWatcher
import android.util.Log
import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.abaferastech.marvelapp.data.model.Comics
import com.abaferastech.marvelapp.data.model.Events
import com.abaferastech.marvelapp.data.model.response.MarvelResponse
import com.abaferastech.marvelapp.data.model.state.State
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.ui.base.BaseViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.kotlin.addTo
import java.util.concurrent.TimeUnit

class SearchViewModel : BaseViewModel() {
    private val repository = MarvelRepository()

    val searchQuery = MutableLiveData<String>("")

    private var _searchResult = MutableLiveData<List<Comics>>()

    val searchResult: LiveData<List<Comics>> get() = _searchResult

    fun search(searchQuery: String) {
        Observable.just(searchQuery)
            .debounce(3000, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .flatMap {
                repository.searchInComics(it).toObservable()
            }.subscribe(::onSuccess, ::onError)
            .addTo(compositeDisposable)
    }

    private fun onError(e: Throwable) {
        Log.e("MarvelAPI", "getMarvelEvents() - Error: ${e.message}")
    }

    private fun onSuccess(state: State<MarvelResponse<Comics>>) {
        when (state) {
            is State.Error -> TODO()
            State.Loading -> TODO()
            is State.Success -> {
                _searchResult.postValue(state.toData()?.data?.results)
            }
        }
    }

}
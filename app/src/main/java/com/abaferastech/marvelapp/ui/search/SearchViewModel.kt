package com.abaferastech.marvelapp.ui.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.abaferastech.marvelapp.data.model.result.Comics
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.ui.base.BaseViewModel
import com.abaferastech.marvelapp.ui.model.UIState
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.subjects.PublishSubject
import java.util.concurrent.TimeUnit

class SearchViewModel : BaseViewModel() {
    private val repository = MarvelRepository()

    val searchQuery = MutableLiveData("")

    private val searchObserver: PublishSubject<String> = PublishSubject.create()

    private var _searchResult = MutableLiveData<UIState<List<Comics>>>()
    val searchResult: LiveData<UIState<List<Comics>>> get() = _searchResult

    init {
        searchObserver
            .debounce(2, TimeUnit.SECONDS)
            .flatMap {
                Log.i("dfa: ", it)
                repository.searchInComics(it).toObservable()
            }
            .distinctUntilChanged()
            .subscribe(_searchResult::postValue, ::onError)
            .addTo(compositeDisposable)
    }

    fun search(searchQuery: String) {
        if (searchQuery != "") {
            searchObserver.onNext(searchQuery)
        }
    }

    private fun onError(e: Throwable) {
        Log.e("MarvelAPI", "getMarvelEvents() - Error: ${e.message}")
    }

}
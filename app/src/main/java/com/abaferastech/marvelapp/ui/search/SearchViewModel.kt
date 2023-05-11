package com.abaferastech.marvelapp.ui.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.abaferastech.marvelapp.data.model.result.Comics
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.ui.base.BaseViewModel
import com.abaferastech.marvelapp.ui.model.SearchItem
import com.abaferastech.marvelapp.ui.model.TYPE
import com.abaferastech.marvelapp.ui.model.UIState
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.subjects.PublishSubject
import java.util.concurrent.TimeUnit

class SearchViewModel : BaseViewModel() {
    private val repository = MarvelRepository()


    val searchQuery = MutableLiveData<String>()
    val searchType = MutableLiveData<TYPE>()

    private val searchObserver: PublishSubject<String> = PublishSubject.create()

    private var _searchingResponse = MutableLiveData<UIState<List<Comics>>>()
    val searchingResponse: LiveData<UIState<List<Comics>>> get() = _searchingResponse

    val searchResult = MediatorLiveData<SearchItem>().apply {
        addSource(searchQuery) { search(it) }
        addSource(searchType) { search(searchQuery.value.toString()) }
    }


    init {
        searchType.postValue(TYPE.COMIC)
        searchObserver
            .debounce(2, TimeUnit.SECONDS)
            .flatMap {
                Log.i("dfa: ", "query $it")
                Log.i("dfa: ", "${searchType.value}")
                when (searchType.value) {
                    TYPE.COMIC -> repository.searchInComics(it).toObservable()
                    TYPE.SERIES -> TODO()
                    TYPE.CHARACTER -> TODO()
                    TYPE.EVENT -> TODO()
                    TYPE.CREATOR -> TODO()
                    null -> TODO()
                }

            }
//            .distinctUntilChanged()
            .subscribe(_searchingResponse::postValue, ::onError)
            .addTo(compositeDisposable)

    }

    fun search(searchQuery: String) {
        if (searchQuery != "") {
            searchObserver.onNext(searchQuery)
        }
    }

    fun changeSearchType(type: TYPE) {
        searchType.postValue(type)
    }

    private fun onError(errorMessage: Throwable) {
        _searchingResponse.postValue(UIState.Error(errorMessage.message.toString()))
    }

}
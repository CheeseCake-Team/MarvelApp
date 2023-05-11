package com.abaferastech.marvelapp.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.abaferastech.marvelapp.data.model.result.Characters
import com.abaferastech.marvelapp.data.model.result.Comics
import com.abaferastech.marvelapp.data.model.result.Events
import com.abaferastech.marvelapp.data.model.result.Series
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.ui.base.BaseViewModel
import com.abaferastech.marvelapp.ui.model.SearchItem
import com.abaferastech.marvelapp.ui.model.TYPE
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.subjects.PublishSubject
import java.util.concurrent.TimeUnit

class SearchViewModel : BaseViewModel() {
    private val repository = MarvelRepository()

    val isLoading = MutableLiveData<Boolean>(false)

    val searchQuery = MutableLiveData<String>()

    private val _searchType = MutableLiveData(TYPE.COMIC)

    val isChipGroupVisible = MutableLiveData<Boolean>(false)

    val searchType: LiveData<TYPE> get() = _searchType

    private val searchObserver: PublishSubject<String> = PublishSubject.create()

    private var _searchingResponse = MutableLiveData<SearchItem>()

    val searchingResponse: LiveData<SearchItem> get() = _searchingResponse

    init {
        searchObserver
            .debounce(2, TimeUnit.SECONDS)
            .flatMap { searchQuery ->
                when (searchType.value) {
                    TYPE.SERIES -> repository.searchInSeries(searchQuery).toObservable()
                        .map {
                            isLoading.postValue(false)
                            SearchItem.Series(it.toData() as List<Series>)
                        }

                    TYPE.CHARACTER -> repository.searchInCharacters(searchQuery).toObservable()
                        .map {
                            isLoading.postValue(false)
                            SearchItem.Character(it.toData() as List<Characters>)
                        }

                    TYPE.EVENT -> repository.searchInEvents(searchQuery).toObservable()
                        .map {
                            isLoading.postValue(false)
                            SearchItem.Event(it.toData() as List<Events>)
                        }

                    else -> repository.searchInComics(searchQuery).toObservable().map {
                        isLoading.postValue(false)
                        SearchItem.Comic(it.toData() as List<Comics>)
                    }

                }
            }.doOnNext {
                isLoading.postValue(false)
            }
            .subscribe(_searchingResponse::postValue)
            .addTo(compositeDisposable)

    }

    fun search(searchQuery: String) {
        isLoading.postValue(true)
        if (searchQuery != "") {
            searchObserver.onNext(searchQuery)
        }
    }

    fun changeSearchType(type: TYPE) {
        _searchType.postValue(type)
        search(searchQuery.value.toString())
    }

    /*private fun onError(errorMessage: Throwable) {
        _searchingResponse.postValue(UIState.Error(errorMessage.message.toString()))
    }*/

    fun toggleChipGroupVisibility() {
        isChipGroupVisible.value = !(isChipGroupVisible.value ?: false)
    }


}
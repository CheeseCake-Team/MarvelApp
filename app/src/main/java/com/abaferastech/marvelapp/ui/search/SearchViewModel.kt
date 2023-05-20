package com.abaferastech.marvelapp.ui.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.domain.models.Character
import com.abaferastech.marvelapp.domain.models.Comic
import com.abaferastech.marvelapp.domain.models.Event
import com.abaferastech.marvelapp.domain.models.SearchQuery
import com.abaferastech.marvelapp.domain.models.Series
import com.abaferastech.marvelapp.ui.base.BaseViewModel
import com.abaferastech.marvelapp.ui.character.characters.CharactersInteractionListener
import com.abaferastech.marvelapp.ui.comic.comics.ComicsInteractionListener
import com.abaferastech.marvelapp.ui.event.events.EventsInteractionListener
import com.abaferastech.marvelapp.ui.model.EventModel
import com.abaferastech.marvelapp.ui.model.SearchItem
import com.abaferastech.marvelapp.ui.model.TYPE
import com.abaferastech.marvelapp.ui.model.UIState
import com.abaferastech.marvelapp.ui.series.seriesViewAll.SeriesViewAllInteractionListener
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel

class SearchViewModel @Inject constructor(val repository: MarvelRepository) : BaseViewModel(),
    CharactersInteractionListener, EventsInteractionListener, SeriesViewAllInteractionListener,
    ComicsInteractionListener, OldQueryListener {

    private val _oldSearchQueries = MutableLiveData<List<SearchQuery>>()
    val oldSearchQueries: LiveData<List<SearchQuery>> get() = _oldSearchQueries

    val searchQuery = MutableLiveData("")

    private val _searchType = MutableLiveData(TYPE.COMIC)
    val searchType: LiveData<TYPE> get() = _searchType

    private var _searchingResponse = MutableLiveData<UIState<SearchItem>>()
    val searchingResponse = _searchingResponse

    val navigationEvents = MutableLiveData<EventModel<SearchEvents>>()

    init {
        getOldSearchQueriesFromDatabase()
    }

    private fun onSuccess(searchItem: SearchItem) {
        _searchingResponse.postValue(UIState.Success(searchItem))
    }

    private fun onError(throwable: Throwable) {
        Log.e("error while fetching", throwable.message.toString())
        _searchingResponse.postValue(UIState.Error(throwable.message.toString()))
    }

    private fun getOldSearchQueriesFromDatabase() {
        repository.getAllSearchQueries()
            .subscribe(_oldSearchQueries::postValue) {
                Log.e("error while fetching", it.message.toString())
            }.addTo(compositeDisposable)
    }

    fun search(searchQuery: String) {
        if (searchQuery != "") {
            Observable.just(searchQuery)
                .flatMap { mySearchQuery ->
                    _searchingResponse.postValue(UIState.Loading)
                    when (searchType.value) {
                        TYPE.SERIES -> repository.searchInSeries(mySearchQuery).map{ SearchItem.SeriesItem(it)}

                        TYPE.CHARACTER -> repository.searchInCharacters(mySearchQuery)
                            .map{ SearchItem.CharacterItem(it)}

                        TYPE.EVENT -> repository.searchInEvents(mySearchQuery).map{ SearchItem.EventItem(it)}

                        else -> repository.searchInComics(mySearchQuery)
                            .map {  SearchItem.ComicItem(it) }
                    }
                }
                .subscribe(::onSuccess, ::onError)
                .addTo(compositeDisposable)
            this.searchQuery.postValue(searchQuery)
        }
    }

    fun saveSearchQuery(oldQuery: String) {
        Completable.fromAction {
            repository.insertSearchQuery(oldQuery)
        }
            .subscribeOn(Schedulers.io())
            .subscribe {
                Log.e("save", "save Completed")
            }.addTo(compositeDisposable)
    }

    fun changeSearchType(type: TYPE) {
        _searchType.postValue(type)
        search(searchQuery.value.toString())
    }

    override fun onClickCharacter(character: Character) {
        navigationEvents.postValue(EventModel(SearchEvents.ClickCharacterEvent(character.id)))
    }

    override fun onClickComic(comic: Comic) {
        navigationEvents.postValue(EventModel(SearchEvents.ClickComicEvent(comic.id)))
    }

    override fun onEventClick(event: Event) {
        navigationEvents.postValue(EventModel(SearchEvents.ClickEventEvent(event.id)))
    }

    override fun onClickSeries(series: Series) {
        navigationEvents.postValue(EventModel(SearchEvents.ClickSeriesEvent(series.id)))
    }

    override fun onSearchQueryClick(oldQueryEntity: SearchQuery) {
        search(oldQueryEntity.searchQuery)
        navigationEvents.postValue(EventModel(SearchEvents.HideSearchViewEvent(oldQueryEntity.searchQuery)))
    }

    override fun onDeleteClick(oldQueryEntity: SearchQuery) {
        Completable.fromAction {
            repository.deleteSearchQuery(oldQueryEntity)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.e("delete", "delete Completed")
            }.addTo(compositeDisposable)
    }
}
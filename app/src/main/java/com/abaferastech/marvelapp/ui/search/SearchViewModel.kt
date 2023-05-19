package com.abaferastech.marvelapp.ui.search

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
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel

class SearchViewModel @Inject constructor(val repository: MarvelRepository) : BaseViewModel(),
    CharactersInteractionListener, EventsInteractionListener, SeriesViewAllInteractionListener,
    ComicsInteractionListener, OldQueryListener {

    private val _oldSearchQueries = MutableLiveData<List<SearchQuery>>()
    val oldSearchQueries = _oldSearchQueries


    private val _searchType = MutableLiveData(TYPE.COMIC)
    val searchType = _searchType

    private val searchObserver: PublishSubject<String> = PublishSubject.create()

    private var _searchingResponse = MutableLiveData<UIState<SearchItem>>()
    val searchingResponse = _searchingResponse

    val searchQuery = MutableLiveData("")

    val searchEvents = MutableLiveData<EventModel<SearchEvents>>()

    init {
        getOldSearchQueriesFromDatabase()
        searchObserver
            .debounce(1, TimeUnit.SECONDS)
            .flatMap { searchQuery ->
                _searchingResponse.postValue(UIState.Loading)
                when (searchType.value) {
                    TYPE.SERIES -> repository.searchInSeries(searchQuery).toObservable()
                        .map { SearchItem.SeriesItem(it.toData() as List<Series>) }

                    TYPE.CHARACTER -> repository.searchInCharacters(searchQuery).toObservable()
                        .map { SearchItem.CharacterItem(it.toData() as List<Character>) }

                    TYPE.EVENT -> repository.searchInEvents(searchQuery).toObservable()
                        .map { SearchItem.EventItem(it.toData() as List<Event>) }

                    else -> repository.searchInComics(searchQuery).toObservable()
                        .map { SearchItem.ComicItem(it.toData() as List<Comic>) }
                }
            }
            .subscribe(::onSuccess, ::onError)
            .addTo(compositeDisposable)
    }

    private fun getOldSearchQueriesFromDatabase() {
        repository.getAllSearchQueries()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError { Log.e("error while fetching", it.message.toString()) }
            .subscribe(_oldSearchQueries::postValue)
            .addTo(compositeDisposable)
    }

    private fun onSuccess(searchItem: SearchItem) {
        _searchingResponse.postValue(UIState.Success(searchItem))
    }

    private fun onError(throwable: Throwable) {
        _searchingResponse.postValue(UIState.Error(throwable.message.toString()))
    }

    fun search(query: String) {
        if (query != "") {
            searchObserver.onNext(query)
            searchQuery.postValue(query)
        }
    }

    fun saveSearchQuery(oldQuery: String) {
        repository.insertSearchQuery(oldQuery)
    }

    fun changeSearchType(type: TYPE) {
        _searchType.postValue(type)
        search(searchQuery.value.toString())
    }

    override fun onClickCharacter(character: com.abaferastech.marvelapp.domain.models.Character) {
        searchEvents.postValue(Event(SearchEvents.ClickCharacterEvent(character.id!!)))
    }

    override fun onClickComic(comic: Comic) {
        searchEvents.postValue(Event(SearchEvents.ClickComicEvent(comic.id!!)))
    }

    override fun onEventClick(event: Event) {
        searchEvents.postValue(Event(SearchEvents.ClickEventEvent(event.id!!)))
    }

    override fun onClickSeries(series: Series) {
        searchEvents.postValue(Event(SearchEvents.ClickSeriesEvent(series.id)))
    }

    override fun onSearchQueryClick(oldQueryEntity: SearchQuery) {
        search(oldQueryEntity.searchQuery)
        searchEvents.postValue(Event(SearchEvents.HideSearchViewEvent(oldQueryEntity.searchQuery)))
    }

    override fun onDeleteClick(oldQueryEntity: SearchQuery) {
        repository.deleteSearchQuery(oldQueryEntity)
    }
}
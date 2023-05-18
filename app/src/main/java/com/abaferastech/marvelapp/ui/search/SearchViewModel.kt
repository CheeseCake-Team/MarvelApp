package com.abaferastech.marvelapp.ui.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.abaferastech.marvelapp.data.local.database.entity.SearchQueryEntity
import com.abaferastech.marvelapp.data.remote.response.CharacterDTO
import com.abaferastech.marvelapp.data.remote.response.ComicDTO
import com.abaferastech.marvelapp.data.remote.response.EventDTO
import com.abaferastech.marvelapp.data.remote.response.SeriesDTO
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.ui.base.BaseViewModel
import com.abaferastech.marvelapp.ui.character.characters.CharactersInteractionListener
import com.abaferastech.marvelapp.ui.comic.comics.ComicsInteractionListener
import com.abaferastech.marvelapp.ui.event.events.EventsInteractionListener
import com.abaferastech.marvelapp.ui.home.adapters.SeriesInteractionListener
import com.abaferastech.marvelapp.ui.model.Event
import com.abaferastech.marvelapp.ui.model.SearchItem
import com.abaferastech.marvelapp.ui.model.TYPE
import dagger.hilt.android.lifecycle.HiltViewModel
import com.abaferastech.marvelapp.ui.model.UIState
import com.abaferastech.marvelapp.ui.series.seriesViewAll.SeriesViewAllInteractionListener
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import java.util.concurrent.TimeUnit
import javax.inject.Inject
@HiltViewModel

class SearchViewModel @Inject constructor(val repository: MarvelRepository) : BaseViewModel(),
    CharactersInteractionListener, EventsInteractionListener, SeriesViewAllInteractionListener,
    ComicsInteractionListener, OldQueryListener {

    private val _oldSearchQueries = MutableLiveData<List<SearchQueryEntity>>()
    val oldSearchQueries: LiveData<List<SearchQueryEntity>> get() = _oldSearchQueries

    val searchQuery = MutableLiveData("")

    private val _searchType = MutableLiveData(TYPE.COMIC)
    val searchType: LiveData<TYPE> get() = _searchType

    private val searchObserver: PublishSubject<String> = PublishSubject.create()

    private var _searchingResponse = MutableLiveData<UIState<SearchItem>>()
    val searchingResponse = _searchingResponse

    val searchEvents = MutableLiveData<Event<SearchEvents>>()

    init {
        getOldSearchQueriesFromDatabase()
        searchObserver
            .debounce(1, TimeUnit.SECONDS)
            .flatMap { searchQuery ->
                _searchingResponse.postValue(UIState.Loading)
                when (searchType.value) {
                    TYPE.SERIES -> repository.searchInSeries(searchQuery).toObservable()
                        .map { SearchItem.Series(it.toData() as List<SeriesDTO>) }

                    TYPE.CHARACTER -> repository.searchInCharacters(searchQuery).toObservable()
                        .map { SearchItem.Character(it.toData() as List<CharacterDTO>) }

                    TYPE.EVENT -> repository.searchInEvents(searchQuery).toObservable()
                        .map { SearchItem.Event(it.toData() as List<EventDTO>) }

                    else -> repository.searchInComics(searchQuery).toObservable()
                        .map { SearchItem.Comic(it.toData() as List<ComicDTO>) }
                }
            }
            .subscribe(::onSuccess, ::onError)
            .addTo(compositeDisposable)
    }

    private fun getOldSearchQueriesFromDatabase() {
        repository.getAllSearchQueries()
            .subscribe(_oldSearchQueries::postValue) {
            Log.e("error while fetching", it.message.toString())
        }.addTo(compositeDisposable)
    }

    private fun onSuccess(searchItem: SearchItem) {
        _searchingResponse.postValue(UIState.Success(searchItem))
    }

    private fun onError(throwable: Throwable) {
        _searchingResponse.postValue(UIState.Error(throwable.message.toString()))
    }

    fun search(searchQuery: String) {
        if (searchQuery != "") {
            searchObserver.onNext(searchQuery)
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

    override fun onClickCharacter(character: CharacterDTO) {
        searchEvents.postValue(Event(SearchEvents.ClickCharacterEvent(character.id!!)))
    }

    override fun onClickComic(comic: ComicDTO) {
        searchEvents.postValue(Event(SearchEvents.ClickComicEvent(comic.id!!)))
    }

    override fun onEventClick(event: EventDTO) {
        searchEvents.postValue(Event(SearchEvents.ClickEventEvent(event.id!!)))
    }

    override fun onClickSeries(series: SeriesDTO) {
        searchEvents.postValue(Event(SearchEvents.ClickSeriesEvent(series.id)))
    }

    override fun onSearchQueryClick(oldQueryEntity: SearchQueryEntity) {
        search(oldQueryEntity.searchQuery)
        searchEvents.postValue(Event(SearchEvents.HideSearchViewEvent(oldQueryEntity.searchQuery)))
    }

    override fun onDeleteClick(oldQueryEntity: SearchQueryEntity) {
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
package com.abaferastech.marvelapp.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.abaferastech.marvelapp.data.model.result.Characters
import com.abaferastech.marvelapp.data.model.result.Comics
import com.abaferastech.marvelapp.data.model.result.Events
import com.abaferastech.marvelapp.data.model.result.Series
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.ui.base.BaseViewModel
import com.abaferastech.marvelapp.ui.character.characters.CharactersInteractionListener
import com.abaferastech.marvelapp.ui.comic.comics.ComicsInteractionListener
import com.abaferastech.marvelapp.ui.event.events.EventsInteractionListener
import com.abaferastech.marvelapp.ui.home.adapters.SeriesInteractionListener
import com.abaferastech.marvelapp.ui.model.Event
import com.abaferastech.marvelapp.ui.model.SearchItem
import com.abaferastech.marvelapp.ui.model.TYPE
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.subjects.PublishSubject
import java.util.concurrent.TimeUnit

class SearchViewModel : BaseViewModel(),
    CharactersInteractionListener, EventsInteractionListener, SeriesInteractionListener,
    ComicsInteractionListener {
    private val repository = MarvelRepository()

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> get() = _isLoading

    val searchQuery = MutableLiveData<String>()

    private val _searchType = MutableLiveData(TYPE.COMIC)

    private val _isChipGroupVisible = MutableLiveData<Boolean>(false)

    val isChipGroupVisible: LiveData<Boolean> get() = _isChipGroupVisible

    val searchType: LiveData<TYPE> get() = _searchType

    private val searchObserver: PublishSubject<String> = PublishSubject.create()

    private var _searchingResponse = MutableLiveData<SearchItem>()

    val searchingResponse: LiveData<SearchItem> get() = _searchingResponse


    val navigationEvents = MutableLiveData<Event<SearchEvents>>()


    init {
        searchObserver
            .doOnNext {
                _isLoading.postValue(true)
            }
            .debounce(1, TimeUnit.SECONDS)
            .flatMap { searchQuery ->
                when (searchType.value) {
                    TYPE.SERIES -> repository.searchInSeries(searchQuery).toObservable()
                        .map {
                            SearchItem.Series(it.toData() as List<Series>)
                        }

                    TYPE.CHARACTER -> repository.searchInCharacters(searchQuery).toObservable()
                        .map {
                            SearchItem.Character(it.toData() as List<Characters>)
                        }

                    TYPE.EVENT -> repository.searchInEvents(searchQuery).toObservable()
                        .map {
                            SearchItem.Event(it.toData() as List<Events>)
                        }

                    else -> repository.searchInComics(searchQuery).toObservable().map {
                        SearchItem.Comic(it.toData() as List<Comics>)
                    }

                }
            }.doOnNext {
                _isLoading.postValue(false)
            }
            .subscribe(_searchingResponse::postValue)
            .addTo(compositeDisposable)

    }

    fun search(searchQuery: String) {
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
        _isChipGroupVisible.value = !(isChipGroupVisible.value ?: false)
    }

    override fun onClickCharacter(character: Characters) {
        navigationEvents.postValue(Event(SearchEvents.ClickCharacterEvent(character.id!!)))
    }

    override fun onClickComic(comic: Comics) {
        navigationEvents.postValue(Event(SearchEvents.ClickComicEvent(comic.id!!)))
    }

    override fun onEventClick(event: Events) {
        navigationEvents.postValue(Event(SearchEvents.ClickEventEvent(event.id!!)))
    }

    override fun onClickSeries(series: Series) {
        navigationEvents.postValue(Event(SearchEvents.ClickSeriesEvent(series.id)))
    }


}
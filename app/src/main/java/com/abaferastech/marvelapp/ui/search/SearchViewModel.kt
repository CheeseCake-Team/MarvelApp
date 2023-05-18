package com.abaferastech.marvelapp.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.subjects.PublishSubject
import java.util.concurrent.TimeUnit
import javax.inject.Inject
@HiltViewModel

class SearchViewModel @Inject constructor(val repository: MarvelRepository) : BaseViewModel(),
    CharactersInteractionListener, EventsInteractionListener, SeriesInteractionListener,
    ComicsInteractionListener {

    val repository = MarvelRepository()


    private val _searchType = MutableLiveData(TYPE.COMIC)
    val searchType: LiveData<TYPE> get() = _searchType


    private val searchObserver: PublishSubject<String> = PublishSubject.create()

    private var _searchingResponse = MutableLiveData<UIState<SearchItem>>()
    val searchingResponse = _searchingResponse


    val navigationEvents = MutableLiveData<Event<SearchEvents>>()

    init {
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

    private fun onSuccess(searchItem: SearchItem) {

        _searchingResponse.postValue(UIState.Success(searchItem))
    }

    private fun onError(throwable: Throwable) {
        _searchingResponse.postValue(UIState.Error(throwable.message.toString()))

    }

    fun search(searchQuery: String) {
        if (searchQuery != "") {
            searchObserver.onNext(searchQuery)
        }
    }


    fun changeSearchType(type: TYPE) {
        _searchType.postValue(type)
    }

    override fun onClickCharacter(character: CharacterDTO) {
        navigationEvents.postValue(Event(SearchEvents.ClickCharacterEvent(character.id!!)))
    }

    override fun onClickComic(comic: ComicDTO) {
        navigationEvents.postValue(Event(SearchEvents.ClickComicEvent(comic.id!!)))
    }

    override fun onEventClick(event: EventDTO) {
        navigationEvents.postValue(Event(SearchEvents.ClickEventEvent(event.id!!)))
    }

    override fun onClickSeries(series: SeriesDTO) {
        navigationEvents.postValue(Event(SearchEvents.ClickSeriesEvent(series.id)))
    }


}
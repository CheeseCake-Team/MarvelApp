package com.abaferastech.marvelapp.data.repository

import com.abaferastech.marvelapp.data.remote.MarvelApiService
import com.abaferastech.marvelapp.data.remote.response.BaseResponse
import com.abaferastech.marvelapp.domain.mapper.CharacterDomainMapper
import com.abaferastech.marvelapp.domain.mapper.EventMapper
import com.abaferastech.marvelapp.domain.mapper.CreatorMapper
import com.abaferastech.marvelapp.domain.mapper.ComicDominMapper
import com.abaferastech.marvelapp.domain.mapper.SeriesMapper
import com.abaferastech.marvelapp.domain.models.Character
import com.abaferastech.marvelapp.domain.models.Event
import com.abaferastech.marvelapp.domain.models.Creator
import com.abaferastech.marvelapp.domain.models.Comic
import com.abaferastech.marvelapp.domain.models.Series
import com.abaferastech.marvelapp.ui.model.UIState
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

class MarvelRepository @Inject constructor(
    private val apiService: MarvelApiService,
    private val comicMapper: ComicDominMapper,
    private val seriesMapper: SeriesMapper,
    private val creatorMapper: CreatorMapper,
    private val eventMapper: EventMapper,
    private val characterDomainMapper: CharacterDomainMapper
) : IMarvelRepository {

    override fun searchInComics(query: String): Single<UIState<List<Comic>>> {
        return wrapResponseWithState { apiService.searchInComics(query) }
            .mapUIState(comicMapper::map)
    }

    override fun searchInCharacters(query: String): Single<UIState<List<Character>>> {
        return wrapResponseWithState { apiService.searchInCharacters(query) }
            .mapUIState(characterDomainMapper::map)
    }

    override fun searchInEvents(query: String): Single<UIState<List<Event>>> {
        return wrapResponseWithState { apiService.searchInEvents(query) }
            .mapUIState(eventMapper::map)
    }

    override fun searchInSeries(query: String): Single<UIState<List<Series>>> {
        return wrapResponseWithState { apiService.searchInSeries(query) }
            .mapUIState(seriesMapper::map)
    }


    override fun getSingleCharacter(characterId: Int): Single<UIState<Character>> {
        return wrapResponseWithState { apiService.getSingleCharacter(characterId) }
            .mapUIState(characterDomainMapper::map).mapListToSingleItem()
    }

    override fun getSingleEvent(eventsId: Int):
            Single<UIState<Event>> {
        return wrapResponseWithState { apiService.getEventsById(eventsId) }
            .mapUIState(eventMapper::map).mapListToSingleItem()
    }


    override fun getSingleSeries(seriesId: Int): Single<UIState<Series>> {
        return wrapResponseWithState { apiService.getSingleSeries(seriesId) }
            .mapUIState(seriesMapper::map).mapListToSingleItem()

    }

    override fun getSingleComic(comicsId: Int): Single<UIState<Comic>> {
        return wrapResponseWithState { apiService.getSingleComic(comicsId) }
            .mapUIState(comicMapper::map).mapListToSingleItem()
    }

    override fun getSingleCreator(creatorId: Int): Single<UIState<Creator>> {
        return wrapResponseWithState { apiService.getSingleCreator(creatorId) }
            .mapUIState(creatorMapper::map).mapListToSingleItem()
    }

    override fun getAllEvents(): Single<UIState<List<Event>>> {
        return wrapResponseWithState { apiService.getAllEvents() }
            .mapUIState(eventMapper::map)
    }

    override fun getAllCharacters(): Single<UIState<List<Character>>> {
        return wrapResponseWithState { apiService.getAllCharacters() }
            .mapUIState(characterDomainMapper::map)

    }


    override fun getAllComics(): Single<UIState<List<Comic>>> {
        return wrapResponseWithState { apiService.getAllComics() }
            .mapUIState(comicMapper::map)
    }

    override fun getAllCreators(): Single<UIState<List<Creator>>> {
        return wrapResponseWithState { apiService.getAllCreators() }
            .mapUIState(creatorMapper::map)
    }


    override fun getEventComics(eventId: Int): Single<UIState<List<Comic>>> {
        return wrapResponseWithState { apiService.getSeriesComics(eventId) }
            .mapUIState(comicMapper::map)
    }


    override fun getCharacterEvents(characterId: Int): Single<UIState<List<Event>>> {
        return wrapResponseWithState { apiService.getCharacterEvents(characterId) }
            .mapUIState(eventMapper::map)
    }

    override fun getCharacterComics(characterId: Int): Single<UIState<List<Comic>>> {
        return wrapResponseWithState { apiService.getCharacterComics(characterId) }
            .mapUIState(comicMapper::map)
    }

    override fun getCharacterSeries(characterId: Int): Single<UIState<List<Series>>> {
        return wrapResponseWithState { apiService.getCharacterSeries(characterId) }
            .mapUIState(seriesMapper::map)
    }


    override fun getSeriesFullUrl(fullUrl: String): Single<UIState<List<Series>>> {
        return wrapResponseWithState { apiService.getSeriesFullUrl(fullUrl) }
            .mapUIState(seriesMapper::map)
    }

    override fun getSeriesComics(seriesId: Int): Single<UIState<List<Comic>>> {
        return wrapResponseWithState { apiService.getSeriesComics(seriesId) }
            .mapUIState(comicMapper::map)
    }

    override fun getSeriesEvents(seriesId: Int): Single<UIState<List<Event>>> {
        return wrapResponseWithState { apiService.getSeriesEvents(seriesId) }
            .mapUIState(eventMapper::map)
    }

    override fun getSeriesCharacters(seriesId: Int): Single<UIState<List<Character>>> {
        return wrapResponseWithState { apiService.getSeriesCharacters(seriesId) }
            .mapUIState(characterDomainMapper::map)
    }

    override fun getEventCharacters(eventId: Int): Single<UIState<List<Character>>> {
        return wrapResponseWithState { apiService.getEventCharacters(eventId) }
            .mapUIState(characterDomainMapper::map)
    }


    override fun getAllSeries(): Single<UIState<List<Series>>> {
        return wrapResponseWithState { apiService.getAllSeries() }
            .mapUIState(seriesMapper::map)
    }


    override fun getComicEvents(comicsId: Int): Single<UIState<List<Event>>> {
        return wrapResponseWithState { apiService.getComicEvents(comicsId) }
            .mapUIState(eventMapper::map)
    }

    override fun getComicCharacters(comicsId: Int): Single<UIState<List<Character>>> {
        return wrapResponseWithState { apiService.getComicCharacters(comicsId) }
            .mapUIState(characterDomainMapper::map)
    }

    override fun getComicSeries(comicsId: Int): Single<UIState<List<Series>>> {
        return wrapResponseWithState { apiService.getComicSeries(comicsId) }
            .mapUIState(seriesMapper::map)
    }


    override fun getEventSeries(comicsId: Int): Single<UIState<List<Series>>> {
        return wrapResponseWithState { apiService.getEventSeries(comicsId) }
            .mapUIState(seriesMapper::map)
    }


    override fun getSeriesCreators(creatorId: Int): Single<UIState<List<Creator>>> {
        return wrapResponseWithState { apiService.getSeriesCreators(creatorId) }
            .mapUIState(creatorMapper::map)
    }

    override fun getComicCreators(comicsId: Int): Single<UIState<List<Creator>>> {
        return wrapResponseWithState { apiService.getComicCreators(comicsId) }
            .mapUIState(creatorMapper::map)
    }

    override fun getCreatorEvents(creatorId: Int): Single<UIState<List<Event>>> {
        return wrapResponseWithState { apiService.getCreatorEvents(creatorId) }
            .mapUIState(eventMapper::map)
    }

    override fun getCreatorCharacters(creatorId: Int): Single<UIState<List<Character>>> {
        return wrapResponseWithState { apiService.getCreatorCharacters(creatorId) }
            .mapUIState(characterDomainMapper::map)
    }

    override fun getCreatorComics(creatorId: Int): Single<UIState<List<Comic>>> {
        return wrapResponseWithState { apiService.getSeriesComics(creatorId) }
            .mapUIState(comicMapper::map)
    }

    override fun getCreatorSeries(creatorId: Int): Single<UIState<List<Series>>> {
        return wrapResponseWithState { apiService.getCreatorSeries(creatorId) }
            .mapUIState(seriesMapper::map)
    }





    override fun <I, O> refreshDatabaseWithWrapResponse(
        request: () -> Single<Response<BaseResponse<I>>>,
        mapper: (List<I>?) -> List<O>,
        insertIntoDatabase: (List<O>) -> Unit
    ) {
        request().map {
            if (it.isSuccessful) {
                val items = it.body()?.data?.results
                mapper(items).let(insertIntoDatabase)
            } else {
                throw Throwable()
            }
        }
    }


    override fun <T> wrapResponseWithState(request: () -> Single<Response<BaseResponse<T>>>):
            Single<UIState<List<T>>> {
        return request().map {
            if (it.isSuccessful) {
                UIState.Success(it.body()?.data?.results)
            } else {
                UIState.Error(it.message())
            }
        }
    }

    override fun <T> Single<UIState<List<T>>>.mapListToSingleItem(): Single<UIState<T>> {
        return this.map { state ->
            when (state) {
                is UIState.Success -> {
                    val firstItem = state.data?.firstOrNull()
                    if (firstItem != null) {
                        UIState.Success(firstItem)
                    } else {
                        UIState.Error("No results found.")
                    }
                }

                is UIState.Error -> {
                    state
                }

                is UIState.Loading -> {
                    state
                }
            }
        }
    }

    override fun <T, O> Single<UIState<List<T>>>.mapUIState(mapper: (List<T>) -> List<O>)
            : Single<UIState<List<O>>> {
        return this.map { uiState ->
            when (uiState) {
                is UIState.Success -> {
                    val dataList = uiState.data
                    val transformedList = mapper(dataList!!)
                    UIState.Success(transformedList)
                }

                is UIState.Error -> uiState
                is UIState.Loading -> uiState
            }
        }
    }

}
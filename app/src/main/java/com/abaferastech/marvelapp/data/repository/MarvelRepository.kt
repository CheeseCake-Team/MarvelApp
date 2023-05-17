package com.abaferastech.marvelapp.data.repository

import com.abaferastech.marvelapp.data.local.database.MarvelDatabase
import com.abaferastech.marvelapp.data.mapper.entitiytodomain.ComicMapper
import com.abaferastech.marvelapp.data.domain.models.Comic
import com.abaferastech.marvelapp.data.remote.response.BaseResponse
import com.abaferastech.marvelapp.data.remote.response.CharacterDTO
import com.abaferastech.marvelapp.data.remote.response.ComicDTO
import com.abaferastech.marvelapp.data.remote.response.CreatorDTO
import com.abaferastech.marvelapp.data.remote.response.EventDTO
import com.abaferastech.marvelapp.data.remote.response.SeriesDTO
import com.abaferastech.marvelapp.ui.model.UIState
import com.abaferastech.marvelapp.data.remote.MarvelApiService
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MarvelRepository @Inject constructor(
    private val marvelDatabase: MarvelDatabase,
    private val apiService: MarvelApiService
) {

    fun searchInComics(query: String): Single<UIState<List<ComicDTO>>> {
        return wrapWithState { apiService.searchInComics(query) }
    }

    fun searchInCharacters(query: String): Single<UIState<List<CharacterDTO>>> {
        return wrapWithState { apiService.searchInCharacters(query) }
    }

    fun searchInEvents(query: String): Single<UIState<List<EventDTO>>> {
        return wrapWithState { apiService.searchInEvents(query) }
    }

    fun searchInSeries(query: String): Single<UIState<List<SeriesDTO>>> {
        return wrapWithState { apiService.searchInSeries(query) }
    }

    fun getSingleCharacter(characterId: Int): Single<UIState<CharacterDTO>> {
        return wrapWithState { apiService.getSingleCharacter(characterId) }.mapListToSingleItem()
    }

    fun getSingleEvent(eventsId: Int): Single<UIState<EventDTO>> {
        return wrapWithState { apiService.getEventsById(eventsId) }.mapListToSingleItem()
    }

    fun getSingleSeries(seriesId: Int): Single<UIState<SeriesDTO>> {
        return wrapWithState { apiService.getSingleSeries(seriesId) }.mapListToSingleItem()
    }

    fun getSingleComic(comicsId: Int): Single<UIState<ComicDTO>> {
        return wrapWithState { apiService.getSingleComic(comicsId) }.mapListToSingleItem()
    }


    fun getSingleCreator(creatorId: Int): Single<UIState<CreatorDTO>> {
        return wrapWithState { apiService.getSingleCreator(creatorId) }.mapListToSingleItem()
    }

    fun getAllEvents(): Single<UIState<List<EventDTO>>> {
        return wrapWithState { apiService.getAllEvents() }
    }

    fun getAllCharacters(): Single<UIState<List<CharacterDTO>>> {
        return wrapWithState { apiService.getAllCharacters() }
    }

    fun getAllSeries(): Single<UIState<List<SeriesDTO>>> {
        return wrapWithState { apiService.getAllSeries() }
    }

    fun getAllComics(): Single<UIState<List<ComicDTO>>> {
        return wrapWithState { apiService.getAllComics() }
    }

    fun getAllCreators(): Single<UIState<List<CreatorDTO>>> {
        return wrapWithState { apiService.getAllCreators() }
    }


    fun getEventComics(eventId: Int): Single<UIState<List<ComicDTO>>> {
        return wrapWithState { apiService.getEventComics(eventId) }
    }


    fun getCharacterEvents(characterId: Int): Single<UIState<List<EventDTO>>> {
        return wrapWithState { apiService.getCharacterEvents(characterId) }
    }

    fun getCharacterComics(characterId: Int): Single<UIState<List<ComicDTO>>> {
        return wrapWithState { apiService.getCharacterComics(characterId) }
    }

    fun getCharacterSeries(characterId: Int): Single<UIState<List<SeriesDTO>>> {
        return wrapWithState { apiService.getCharacterSeries(characterId) }
    }


    fun getSeriesFullUrl(fullUrl: String): Single<UIState<List<SeriesDTO>>> {
        return wrapWithState { apiService.getSeriesFullUrl(fullUrl) }
    }

    fun getSeriesComics(seriesId: Int): Single<UIState<List<ComicDTO>>> {
        return wrapWithState { apiService.getSeriesComics(seriesId) }
    }

    fun getSeriesEvents(seriesId: Int): Single<UIState<List<EventDTO>>> {
        return wrapWithState { apiService.getSeriesEvents(seriesId) }
    }

    fun getSeriesCharacters(seriesId: Int): Single<UIState<List<CharacterDTO>>> {
        return wrapWithState { apiService.getSeriesCharacters(seriesId) }
    }

    fun getEventCharacters(eventId: Int): Single<UIState<List<CharacterDTO>>> {
        return wrapWithState { apiService.getEventCharacters(eventId) }
    }


    fun getSeriesCreators(seriesId: Int): Single<UIState<List<CreatorDTO>>> {
        return wrapWithState { apiService.getSeriesCreators(seriesId) }
    }


    fun getComicEvents(comicsId: Int): Single<UIState<List<EventDTO>>> {
        return wrapWithState { apiService.getComicEvents(comicsId) }
    }

    fun getComicCharacters(comicsId: Int): Single<UIState<List<CharacterDTO>>> {
        return wrapWithState { apiService.getComicCharacters(comicsId) }
    }

    fun getComicSeries(comicsId: Int): Single<UIState<List<SeriesDTO>>> {
        return wrapWithState { apiService.getComicSeries(comicsId) }
    }

    fun getEventSeries(comicsId: Int): Single<UIState<List<SeriesDTO>>> {
        return wrapWithState { apiService.getEventSeries(comicsId) }
    }

    fun getComicCreators(comicsId: Int): Single<UIState<List<CreatorDTO>>> {
        return wrapWithState { apiService.getComicCreators(comicsId) }
    }


    fun getCreatorEvents(creatorId: Int): Single<UIState<List<EventDTO>>> {
        return wrapWithState { apiService.getCreatorEvents(creatorId) }
    }

    fun getCreatorCharacters(creatorId: Int): Single<UIState<List<CharacterDTO>>> {
        return wrapWithState { apiService.getCreatorCharacters(creatorId) }
    }

    fun getCreatorComics(creatorId: Int): Single<UIState<List<ComicDTO>>> {
        return wrapWithState { apiService.getCreatorComics(creatorId) }
    }

    fun getCreatorSeries(creatorId: Int): Single<UIState<List<SeriesDTO>>> {
        return wrapWithState { apiService.getCreatorSeries(creatorId) }
    }

    private fun <T> wrapWithState(function: () -> Single<Response<BaseResponse<T>>>):
            Single<UIState<List<T>>> {
        return function().map {
            if (it.isSuccessful) {
                UIState.Success(it.body()?.data?.results)
            } else {
                UIState.Error(it.message())
            }
        }
    }

    private fun <T> Single<UIState<List<T>>>.mapListToSingleItem(): Single<UIState<T>> {
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

}
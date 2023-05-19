package com.abaferastech.marvelapp.data.repository
import android.util.Log
import com.abaferastech.marvelapp.data.local.database.MarvelDatabase
import com.abaferastech.marvelapp.data.local.database.daos.CharacterDao
import com.abaferastech.marvelapp.data.local.mappers.CharacterMapper
import com.abaferastech.marvelapp.data.remote.MarvelApiService
import com.abaferastech.marvelapp.data.remote.response.BaseResponse
import com.abaferastech.marvelapp.data.remote.response.CharacterDTO
import com.abaferastech.marvelapp.data.remote.response.ComicDTO
import com.abaferastech.marvelapp.data.remote.response.CreatorDTO
import com.abaferastech.marvelapp.data.remote.response.EventDTO
import com.abaferastech.marvelapp.data.remote.response.SeriesDTO
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
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

class MarvelRepository @Inject constructor(
    private val characterDao: CharacterDao,
    private val apiService: MarvelApiService
) {

    private val comicMapper: ComicDominMapper = ComicDominMapper()
    private val seriesMapper: SeriesMapper = SeriesMapper()
    private val creatorMapper: CreatorMapper = CreatorMapper()
    private val eventMapper: EventMapper = EventMapper()
    
    fun searchInComics(query: String): Single<UIState<List<Comic>>> {
        return wrapResponseWithState { apiService.searchInComics(query) }
            .mapUIState(comicMapper::map)
    }

    fun searchInCharacters(query: String): Single<UIState<List<CharacterDTO>>> {
        return wrapResponseWithState { apiService.searchInCharacters(query) }
    }

    fun searchInEvents(query: String): Single<UIState<List<Event>>> {
        return wrapResponseWithState { apiService.searchInEvents(query) }
            .mapUIState (eventMapper::map)
    }

    fun searchInSeries(query: String): Single<UIState<List<Series>>> {
        return wrapResponseWithState { apiService.searchInSeries(query) }
            .mapUIState(seriesMapper::map)
    }


    fun getSingleCharacter(characterId: Int): Single<UIState<com.abaferastech.marvelapp.domain.models.Character>> {
        return wrapResponseWithState { apiService.getSingleCharacter(characterId) }.mapUIState(characterDomainMapper::map).mapListToSingleItem()
    }

    fun getSingleEvent(eventsId: Int):
            Single<UIState<Event>> {
        return wrapResponseWithState { apiService.getEventsById(eventsId) }
            .mapUIState(eventMapper::map).mapListToSingleItem()
    }


    fun getSingleSeries(seriesId: Int): Single<UIState<Series>> {
        return wrapResponseWithState { apiService.getSingleSeries(seriesId) }
            .mapUIState(seriesMapper::map).mapListToSingleItem()

    }

    fun getSingleComic(comicsId: Int): Single<UIState<Comic>> {
        return wrapResponseWithState { apiService.getSingleComic(comicsId) }
            .mapUIState(comicMapper::map).mapListToSingleItem()
    }

    fun getSingleCreator(creatorId: Int): Single<UIState<Creator>> {
        return wrapResponseWithState { apiService.getSingleCreator(creatorId) }
            .mapUIState(creatorMapper::map).mapListToSingleItem()
    }

    fun getAllEvents(): Single<UIState<List<Event>>> {
        return wrapResponseWithState { apiService.getAllEvents() }
            .mapUIState (eventMapper::map)
    }

    fun getAllCharacters(): Single<UIState<List<Character>>> {
        return wrapResponseWithState { apiService.getAllCharacters() }
                .mapUIState(characterDomainMapper::map)

    }


    fun getAllComics(): Single<UIState<List<Comic>>> {
        return wrapResponseWithState { apiService.getAllComics() }
            .mapUIState(comicMapper::map)    }

    fun getAllCreators(): Single<UIState<List<Creator>>> {
        return wrapResponseWithState { apiService.getAllCreators() }
            .mapUIState(creatorMapper::map)
    }


    fun getEventComics(eventId: Int): Single<UIState<List<Comic>>> {
        return wrapResponseWithState { apiService.getSeriesComics(eventId) }
            .mapUIState(comicMapper::map)
    }


    fun getCharacterEvents(characterId: Int): Single<UIState<List<Event>>> {
        return wrapResponseWithState { apiService.getCharacterEvents(characterId) }
            .mapUIState (eventMapper::map)
    }

    fun getCharacterComics(characterId: Int): Single<UIState<List<Comic>>> {
        return wrapResponseWithState { apiService.getCharacterComics(characterId) }
            .mapUIState(comicMapper::map)
    }

    fun getCharacterSeries(characterId: Int): Single<UIState<List<Series>>> {
        return wrapResponseWithState { apiService.getCharacterSeries(characterId) }
            .mapUIState(seriesMapper::map)
    }





    fun getSeriesFullUrl(fullUrl: String): Single<UIState<List<Series>>> {
        return wrapResponseWithState { apiService.getSeriesFullUrl(fullUrl) }
            .mapUIState(seriesMapper::map)
    }

    fun getSeriesComics(seriesId: Int): Single<UIState<List<Comic>>> {
        return wrapResponseWithState { apiService.getSeriesComics(seriesId) }
            .mapUIState(comicMapper::map)
    }

    fun getSeriesEvents(seriesId: Int): Single<UIState<List<Event>>> {
        return wrapResponseWithState { apiService.getSeriesEvents(seriesId) }
            .mapUIState(eventMapper::map)
    }

    fun getSeriesCharacters(seriesId: Int): Single<UIState<List<Character>>> {
        return wrapResponseWithState { apiService.getSeriesCharacters(seriesId) }
            .mapUIState(characterDomainMapper::map)
    }

    fun getEventCharacters(eventId: Int): Single<UIState<List<Character>>> {
        return wrapResponseWithState { apiService.getEventCharacters(eventId) }
            .mapUIState(characterDomainMapper::map)
    }


    fun getAllSeries(): Single<UIState<List<Series>>> {
        return wrapResponseWithState { apiService.getAllSeries() }
            .mapUIState(seriesMapper::map)
    }

    fun <T, O> Single<UIState<List<T>>>.mapUIState(mapper: (List<T>) -> List<O>): Single<UIState<List<O>>> {
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

    fun getComicEvents(comicsId: Int): Single<UIState<List<Event>>> {
        return wrapResponseWithState { apiService.getComicEvents(comicsId) }
            .mapUIState(eventMapper::map)
    }

    fun getComicCharacters(comicsId: Int): Single<UIState<List<Character>>> {
        return wrapResponseWithState { apiService.getComicCharacters(comicsId) }
            .mapUIState(characterDomainMapper::map)
    }

    fun getComicSeries(comicsId: Int): Single<UIState<List<Series>>> {
        return wrapResponseWithState { apiService.getComicSeries(comicsId) }
            .mapUIState(seriesMapper::map)
    }




    fun getEventSeries(comicsId: Int): Single<UIState<List<Series>>> {
        return wrapResponseWithState { apiService.getEventSeries(comicsId) }
            .mapUIState(seriesMapper::map)
    }


    fun getSeriesCreators(creatorId: Int): Single<UIState<List<Creator>>> {
        return wrapResponseWithState { apiService.getSeriesCreators(creatorId) }
            .mapUIState (creatorMapper::map)
    }

    fun getComicCreators(comicsId: Int): Single<UIState<List<Creator>>> {
        return wrapResponseWithState { apiService.getComicCreators(comicsId) }
            .mapUIState(creatorMapper::map)
    }

    fun getCreatorEvents(creatorId: Int): Single<UIState<List<Event>>> {
        return wrapResponseWithState { apiService.getCreatorEvents(creatorId) }
            .mapUIState (eventMapper::map)
    }

    fun getCreatorCharacters(creatorId: Int): Single<UIState<List<Character>>> {
        return wrapResponseWithState { apiService.getCreatorCharacters(creatorId) }
            .mapUIState(characterDomainMapper::map)
    }

    fun getCreatorComics(creatorId: Int): Single<UIState<List<Comic>>> {
        return wrapResponseWithState { apiService.getSeriesComics(creatorId) }
            .mapUIState(comicMapper::map)
    }

    fun getCreatorSeries(creatorId: Int): Single<UIState<List<Series>>> {
        return wrapResponseWithState { apiService.getCreatorSeries(creatorId) }
            .mapUIState(seriesMapper::map)
    }

    private val characterMapper = CharacterMapper()
    private val characterDomainMapper = CharacterDomainMapper()




    private fun <I, O> refreshDatabaseWithWrapResponse(
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


    private fun <T> wrapResponseWithState(request: () -> Single<Response<BaseResponse<T>>>):
            Single<UIState<List<T>>> {
        return request().map { response ->
            if (response.isSuccessful) {
                UIState.Success(response.body()?.data?.results)
            } else {
                UIState.Error(response.message())
            }
        }.onErrorReturn { t ->
            Log.e("mujtaba", "1", )
            if (t is IOException) {
                UIState.Error("Network error. Please check your internet connection.")
            } else {
                UIState.Error("An unexpected error occurred.")
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
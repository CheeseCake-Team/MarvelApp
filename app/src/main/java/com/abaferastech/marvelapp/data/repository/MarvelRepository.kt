package com.abaferastech.marvelapp.data.repository

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
import com.abaferastech.marvelapp.domain.mapper.ComicMapper
import com.abaferastech.marvelapp.domain.mapper.SeriesMapper
import com.abaferastech.marvelapp.domain.models.Character
import com.abaferastech.marvelapp.domain.models.Series
import com.abaferastech.marvelapp.ui.model.UIState
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

class MarvelRepository @Inject constructor(
    private val characterDao: CharacterDao,
    private val apiService: MarvelApiService
) {
//    private val comicMapper: ComicMapper = ComicMapper()
    private val seriesMapper: SeriesMapper = SeriesMapper()
    fun searchInComics(query: String): Single<UIState<List<ComicDTO>>> {
        return wrapResponseWithState { apiService.searchInComics(query) }
    }

    fun searchInCharacters(query: String): Single<UIState<List<CharacterDTO>>> {
        return wrapResponseWithState { apiService.searchInCharacters(query) }
    }

    fun searchInEvents(query: String): Single<UIState<List<EventDTO>>> {
        return wrapResponseWithState { apiService.searchInEvents(query) }
    }

    fun searchInSeries(query: String): Single<UIState<List<SeriesDTO>>> {
        return wrapResponseWithState { apiService.searchInSeries(query) }
    }


    fun getSingleCharacter(characterId: Int): Single<UIState<CharacterDTO>> {
        return wrapResponseWithState { apiService.getSingleCharacter(characterId) }.mapListToSingleItem()
    }

    fun getSingleEvent(eventsId: Int): Single<UIState<EventDTO>> {
        return wrapResponseWithState { apiService.getEventsById(eventsId) }.mapListToSingleItem()
    }


    fun getSingleSeries(seriesId: Int): Single<UIState<List<Series>>> {
        return wrapResponseWithState { apiService.getSingleSeries(seriesId) }
            .mapUIState(seriesMapper::map)

    }






    fun getSingleComic(comicsId: Int): Single<UIState<ComicDTO>> {
        return wrapResponseWithState { apiService.getSingleComic(comicsId) }.mapListToSingleItem()
    }

    fun getSingleCreator(creatorId: Int): Single<UIState<CreatorDTO>> {
        return wrapResponseWithState { apiService.getSingleCreator(creatorId) }.mapListToSingleItem()
    }

    fun getAllEvents(): Single<UIState<List<EventDTO>>> {
        return wrapResponseWithState { apiService.getAllEvents() }
    }

    fun getAllCharacters(): Single<UIState<List<CharacterDTO>>> {
        return wrapResponseWithState { apiService.getAllCharacters() }
    }



    fun getAllComics(): Single<UIState<List<ComicDTO>>> {
        return wrapResponseWithState { apiService.getAllComics() }
    }

    fun getAllCreators(): Single<UIState<List<CreatorDTO>>> {
        return wrapResponseWithState { apiService.getAllCreators() }
    }


    fun getEventComics(eventId: Int): Single<UIState<List<ComicDTO>>> {
        return wrapResponseWithState { apiService.getEventComics(eventId) }
    }


    fun getCharacterEvents(characterId: Int): Single<UIState<List<EventDTO>>> {
        return wrapResponseWithState { apiService.getCharacterEvents(characterId) }
    }

    fun getCharacterComics(characterId: Int): Single<UIState<List<ComicDTO>>> {
        return wrapResponseWithState { apiService.getCharacterComics(characterId) }
    }

    fun getCharacterSeries(characterId: Int): Single<UIState<List<Series>>> {
        return wrapResponseWithState { apiService.getCharacterSeries(characterId) }
            .mapUIState(seriesMapper::map)
    }





    fun getSeriesFullUrl(fullUrl: String): Single<UIState<List<Series>>> {
        return wrapResponseWithState { apiService.getSeriesFullUrl(fullUrl) }
            .mapUIState(seriesMapper::map)
    }




    fun getSeriesComics(seriesId: Int): Single<UIState<List<ComicDTO>>> {
        return wrapResponseWithState { apiService.getSeriesComics(seriesId) }
    }

    fun getSeriesEvents(seriesId: Int): Single<UIState<List<EventDTO>>> {
        return wrapResponseWithState { apiService.getSeriesEvents(seriesId) }
    }

    fun getSeriesCharacters(seriesId: Int): Single<UIState<List<CharacterDTO>>> {
        return wrapResponseWithState { apiService.getSeriesCharacters(seriesId) }
    }

    fun getEventCharacters(eventId: Int): Single<UIState<List<CharacterDTO>>> {
        return wrapResponseWithState { apiService.getEventCharacters(eventId) }
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
                is UIState.Error -> uiState as UIState<List<O>>
                is UIState.Loading -> uiState as UIState<List<O>>
            }
        }
    }

    fun getComicEvents(comicsId: Int): Single<UIState<List<EventDTO>>> {
        return wrapResponseWithState { apiService.getComicEvents(comicsId) }
    }

    fun getComicCharacters(comicsId: Int): Single<UIState<List<CharacterDTO>>> {
        return wrapResponseWithState { apiService.getComicCharacters(comicsId) }
    }

    fun getComicSeries(comicsId: Int): Single<UIState<List<Series>>> {
        return wrapResponseWithState { apiService.getComicSeries(comicsId) }
            .mapUIState(seriesMapper::map)
    }




    fun getEventSeries(comicsId: Int): Single<UIState<List<Series>>> {
        return wrapResponseWithState { apiService.getEventSeries(comicsId) }
            .mapUIState(seriesMapper::map)
    }



    fun getComicCreators(comicsId: Int): Single<UIState<List<CreatorDTO>>> {
        return wrapResponseWithState { apiService.getComicCreators(comicsId) }
    }

    fun getCreatorEvents(creatorId: Int): Single<UIState<List<EventDTO>>> {
        return wrapResponseWithState { apiService.getCreatorEvents(creatorId) }
    }

    fun getCreatorCharacters(creatorId: Int): Single<UIState<List<CharacterDTO>>> {
        return wrapResponseWithState { apiService.getCreatorCharacters(creatorId) }
    }

    fun getCreatorComics(creatorId: Int): Single<UIState<List<ComicDTO>>> {
        return wrapResponseWithState { apiService.getCreatorComics(creatorId) }
    }

    fun getCreatorSeries(creatorId: Int): Single<UIState<List<Series>>> {
        return wrapResponseWithState { apiService.getCreatorSeries(creatorId) }
            .mapUIState(seriesMapper::map)
    }

    private val characterMapper = CharacterMapper()
    private val characterDomainMapper = CharacterDomainMapper()


    fun getCachedCharacter(): List<Character> {
        return characterDao.getAllCharacters().map { characterDomainMapper.map(it) }
    }

    fun refreshCharacters() {
        refreshDatabaseWithWrapResponse(
            { apiService.getAllCharacters() },
            { list -> list?.map { characterMapper.map(it) } ?: emptyList() },
            {
                characterDao.insertCharacterList(it)
            }
        )
    }

    private fun <I, O> refreshDatabaseWithWrapResponse(
        request: () -> Single<Response<BaseResponse<I>>>,
        mapper: (List<I>?) -> List<O>,
        insertIntoDatabase: (List<O>) -> Unit
    ) {
        request().map {
            if (it.isSuccessful) {
                val items = it.body()?.data?.results
                mapper(items)?.let(insertIntoDatabase)
            } else {
                throw Throwable()
            }
        }
    }


    private fun <T> wrapResponseWithState(request: () -> Single<Response<BaseResponse<T>>>):
            Single<UIState<List<T>>> {
        return request().map {
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
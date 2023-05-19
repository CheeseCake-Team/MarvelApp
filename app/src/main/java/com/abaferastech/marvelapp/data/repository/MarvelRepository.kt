package com.abaferastech.marvelapp.data.repository

import android.util.Log
import com.abaferastech.marvelapp.data.local.database.daos.CharacterDao
import com.abaferastech.marvelapp.data.local.database.daos.ComicDao
import com.abaferastech.marvelapp.data.local.database.daos.SeriesDao
import com.abaferastech.marvelapp.data.local.mappers.DTOCharacterListMapper
import com.abaferastech.marvelapp.data.local.mappers.DTOComicListMapper
import com.abaferastech.marvelapp.data.local.mappers.DTOSeriesListMapper
import com.abaferastech.marvelapp.data.remote.MarvelApiService
import com.abaferastech.marvelapp.data.remote.response.BaseResponse
import com.abaferastech.marvelapp.data.remote.response.CharacterDTO
import com.abaferastech.marvelapp.data.remote.response.ComicDTO
import com.abaferastech.marvelapp.data.remote.response.CreatorDTO
import com.abaferastech.marvelapp.data.remote.response.EventDTO
import com.abaferastech.marvelapp.data.remote.response.SeriesDTO
import com.abaferastech.marvelapp.domain.mapper.EntityCharacterListMapper
import com.abaferastech.marvelapp.domain.mapper.EntityComicListMapper
import com.abaferastech.marvelapp.domain.mapper.EntitySeriesListMapper
import com.abaferastech.marvelapp.domain.models.Character
import com.abaferastech.marvelapp.domain.models.Comic
import com.abaferastech.marvelapp.domain.models.Series
import com.abaferastech.marvelapp.ui.model.UIState
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject


class MarvelRepository @Inject constructor(
    private val characterDao: CharacterDao,
    private val seriesDao: SeriesDao,
    private val comicDao: ComicDao,
    private val apiService: MarvelApiService
) {

    private val characterMapper = DTOCharacterListMapper()
    private val characterDomainMapper = EntityCharacterListMapper()

    private val dTOComicListMapper = DTOComicListMapper()
    private val entityComicListMapper = EntityComicListMapper()

    private val dTOSeriesListMapper = DTOSeriesListMapper()
    private val entitySeriesListMapper = EntitySeriesListMapper()


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

    fun getSingleSeries(seriesId: Int): Single<UIState<SeriesDTO>> {
        return wrapResponseWithState { apiService.getSingleSeries(seriesId) }.mapListToSingleItem()
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

    fun getAllSeries(): Single<UIState<List<SeriesDTO>>> {
        return wrapResponseWithState { apiService.getAllSeries() }
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

    fun getCharacterSeries(characterId: Int): Single<UIState<List<SeriesDTO>>> {
        return wrapResponseWithState { apiService.getCharacterSeries(characterId) }
    }


    fun getSeriesFullUrl(fullUrl: String): Single<UIState<List<SeriesDTO>>> {
        return wrapResponseWithState { apiService.getSeriesFullUrl(fullUrl) }
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


    fun getSeriesCreators(seriesId: Int): Single<UIState<List<CreatorDTO>>> {
        return wrapResponseWithState { apiService.getSeriesCreators(seriesId) }
    }


    fun getComicEvents(comicsId: Int): Single<UIState<List<EventDTO>>> {
        return wrapResponseWithState { apiService.getComicEvents(comicsId) }
    }

    fun getComicCharacters(comicsId: Int): Single<UIState<List<CharacterDTO>>> {
        return wrapResponseWithState { apiService.getComicCharacters(comicsId) }
    }

    fun getComicSeries(comicsId: Int): Single<UIState<List<SeriesDTO>>> {
        return wrapResponseWithState { apiService.getComicSeries(comicsId) }
    }

    fun getEventSeries(comicsId: Int): Single<UIState<List<SeriesDTO>>> {
        return wrapResponseWithState { apiService.getEventSeries(comicsId) }
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

    fun getCreatorSeries(creatorId: Int): Single<UIState<List<SeriesDTO>>> {
        return wrapResponseWithState { apiService.getCreatorSeries(creatorId) }
    }


    fun getCachedCharacter(): Single<List<Character>> {
        return characterDao.getAllCharacters().map { characterDomainMapper.map(it) }
    }

    fun getCachedComics(): Single<List<Comic>> {
        return comicDao.getAllComics().map { entityComicListMapper.map(it) }
    }

    fun getCachedSeries(): Single<List<Series>> {
        return seriesDao.getAllSeries().map { entitySeriesListMapper.map(it) }
    }

    fun refreshCharacters() {
        refreshData(
            { apiService.getAllCharacters() },
            { characterMapper.map(it!!) },
            { items -> characterDao.insertCharacterList(items) }
        )
    }

    fun refreshComics() {
        refreshData(
            { apiService.getAllComics() },
            { dTOComicListMapper.map(it!!) },
            { items -> comicDao.insertComicList(items) }
        )
    }

    fun refreshSeries() {
        refreshData(
            { apiService.getAllSeries() },
            { dTOSeriesListMapper.map(it!!) },
            { items -> seriesDao.insertSeriesList(items) }
        )
    }

    fun refreshHome() {
        Log.d("MAMO", "refreshHome: called")
        refreshSeries()
        refreshComics()
        refreshCharacters()
    }

    private fun <I, O> refreshData(
        request: () -> Single<Response<BaseResponse<I>>>,
        mapper: (List<I>?) -> List<O>,
        insertIntoDatabase: (List<O>) -> Completable
    ) {
        refreshDatabaseWithWrapResponse(request, mapper, insertIntoDatabase)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .doOnError { throwable -> throw throwable }
            .subscribe()
    }

    private fun <I, O> refreshDatabaseWithWrapResponse(
        request: () -> Single<Response<BaseResponse<I>>>,
        mapper: (List<I>?) -> List<O>,
        insertIntoDatabase: (List<O>) -> Completable
    ): Completable {
        return request().flatMapCompletable {
            if (it.isSuccessful) {
                val items = it.body()?.data?.results
                insertIntoDatabase(mapper(items))
            } else {
                Completable.error(Throwable())
            }
        }
    }


    private fun <T> wrapResponseWithState(request: () -> Single<Response<BaseResponse<T>>>): Single<UIState<List<T>>> {
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
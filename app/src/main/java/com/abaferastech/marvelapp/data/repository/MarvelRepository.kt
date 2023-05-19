package com.abaferastech.marvelapp.data.repository

import com.abaferastech.marvelapp.data.local.database.daos.MarvelDao
import com.abaferastech.marvelapp.data.local.database.daos.SearchDao
import com.abaferastech.marvelapp.data.local.database.entity.ComicEntity
import com.abaferastech.marvelapp.data.local.database.entity.SearchQueryEntity
import com.abaferastech.marvelapp.data.local.database.entity.search.ComicSearchEntity
import com.abaferastech.marvelapp.data.remote.MarvelApiService
import com.abaferastech.marvelapp.data.remote.response.BaseResponse
import com.abaferastech.marvelapp.domain.mapper.CharacterDomainMapper
import com.abaferastech.marvelapp.domain.mapper.ComicDominMapper
import com.abaferastech.marvelapp.domain.mapper.CreatorMapper
import com.abaferastech.marvelapp.domain.mapper.EventMapper
import com.abaferastech.marvelapp.domain.mapper.SeriesMapper
import com.abaferastech.marvelapp.domain.models.Character
import com.abaferastech.marvelapp.domain.models.Comic
import com.abaferastech.marvelapp.domain.models.Creator
import com.abaferastech.marvelapp.domain.models.Event
import com.abaferastech.marvelapp.domain.models.SearchQuery
import com.abaferastech.marvelapp.domain.models.Series
import com.abaferastech.marvelapp.ui.model.UIState
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import javax.inject.Inject

class MarvelRepository @Inject constructor(
    private val apiService: MarvelApiService,
    private val comicMapper: ComicDominMapper,
    private val seriesMapper: SeriesMapper,
    private val creatorMapper: CreatorMapper,
    private val eventMapper: EventMapper,
    private val characterDomainMapper: CharacterDomainMapper,
    private val searchDao: SearchDao,
    private val marvelDao: MarvelDao,
) : IMarvelRepository {


    fun SearchQuery.asSearchQueryEntity() = SearchQueryEntity(id, searchQuery)
    fun SearchQueryEntity.asSearchQuery() = SearchQuery(id, searchQuery)

    fun Comic.asComicEntity() = ComicEntity(
        id = id,
        title = title,
        description = description,
        issueNumber = issueNumber,
        price = price,
        pageCount = pageCount,
        modified = modified,
        imageUri = imageUri,
    )

    fun ComicSearchEntity.asComic() = Comic(
        id = id,
        title = title,
        description = null,
        issueNumber = issueNumber,
        price = null,
        pageCount = null,
        modified = modified,
        imageUri = imageUri
    )


    fun getAllSearchQueries(): Observable<List<SearchQuery>> {
        return searchDao.getAllSearchQueries().map { it.map { q -> q.asSearchQuery() } }
    }

    fun deleteSearchQuery(searchQueryEntity: SearchQuery) {

        searchDao.deleteSearchQuery(searchQueryEntity.asSearchQueryEntity())
    }

    fun insertSearchQuery(searchQuery: String) {
        searchDao.insertSearchQuery(SearchQueryEntity(searchQuery = searchQuery))
    }

    fun getSearchQueryEntityByQuery(searchQuery: String): SearchQueryEntity =
        searchDao.getSearchQueryEntityByQuery(searchQuery)

    fun refreshComics(): Completable {
        return wrapResponseWithState({ apiService.getAllComics() }, comicMapper::map)
            .flatMapCompletable { uiState ->
                if (uiState is UIState.Success) {
                    val comics = uiState.data
                    Completable.fromAction {
                        comics?.map { it.asComicEntity() }?.let { marvelDao.insertComicList(it) }
                    }
                } else {
                    Completable.error(Throwable("Failed to fetch users"))
                }
            }
    }

    fun getUsers(): Observable<List<ComicEntity>> {
        return marvelDao.getAllComics()
            .filter { users -> users.isNotEmpty() }
            .switchIfEmpty(refreshComics().andThen(marvelDao.getAllComics()))
    }


    override fun searchInComics(query: String): Single<UIState<List<Comic>>> {

        return wrapResponseWithState({ apiService.searchInComics(query) }, comicMapper::map)
    }

    override fun searchInCharacters(query: String): Single<UIState<List<Character>>> {
        return wrapResponseWithState(
            { apiService.searchInCharacters(query) }, characterDomainMapper::map
        )
    }

    override fun searchInEvents(query: String): Single<UIState<List<Event>>> {
        return wrapResponseWithState({ apiService.searchInEvents(query) }, eventMapper::map)
    }

    override fun searchInSeries(query: String): Single<UIState<List<Series>>> {

        return wrapResponseWithState({ apiService.searchInSeries(query) }, seriesMapper::map)
    }


    override fun getSingleCharacter(characterId: Int): Single<UIState<Character>> {
        return wrapResponseWithState(
            { apiService.getSingleCharacter(characterId) }, characterDomainMapper::map
        ).mapListToSingleItem()
    }

    override fun getSingleEvent(eventsId: Int): Single<UIState<Event>> {
        return wrapResponseWithState(
            { apiService.getEventsById(eventsId) }, eventMapper::map
        ).mapListToSingleItem()
    }


    override fun getSingleSeries(seriesId: Int): Single<UIState<Series>> {
        return wrapResponseWithState(
            { apiService.getSingleSeries(seriesId) }, seriesMapper::map
        ).mapListToSingleItem()

    }

    override fun getSingleComic(comicsId: Int): Single<UIState<Comic>> {
        return wrapResponseWithState(
            { apiService.getSingleComic(comicsId) }, comicMapper::map
        ).mapListToSingleItem()
    }

    override fun getSingleCreator(creatorId: Int): Single<UIState<Creator>> {
        return wrapResponseWithState(
            { apiService.getSingleCreator(creatorId) }, creatorMapper::map
        ).mapListToSingleItem()
    }

    override fun getAllEvents(): Single<UIState<List<Event>>> {
        return wrapResponseWithState({ apiService.getAllEvents() }, eventMapper::map)
    }

    override fun getAllCharacters(): Single<UIState<List<Character>>> {
        return wrapResponseWithState({ apiService.getAllCharacters() }, characterDomainMapper::map)

    }


    override fun getAllComics(): Single<UIState<List<Comic>>> {
        return wrapResponseWithState({ apiService.getAllComics() }, comicMapper::map)
    }

    override fun getAllCreators(): Single<UIState<List<Creator>>> {
        return wrapResponseWithState({ apiService.getAllCreators() }, creatorMapper::map)
    }


    override fun getEventComics(eventId: Int): Single<UIState<List<Comic>>> {
        return wrapResponseWithState({ apiService.getSeriesComics(eventId) }, comicMapper::map)
    }


    override fun getCharacterEvents(characterId: Int): Single<UIState<List<Event>>> {
        return wrapResponseWithState(
            { apiService.getCharacterEvents(characterId) }, eventMapper::map
        )
    }

    override fun getCharacterComics(characterId: Int): Single<UIState<List<Comic>>> {
        return wrapResponseWithState(
            { apiService.getCharacterComics(characterId) }, comicMapper::map
        )
    }

    override fun getCharacterSeries(characterId: Int): Single<UIState<List<Series>>> {
        return wrapResponseWithState(
            { apiService.getCharacterSeries(characterId) }, seriesMapper::map
        )
    }


    override fun getSeriesFullUrl(fullUrl: String): Single<UIState<List<Series>>> {
        return wrapResponseWithState({ apiService.getSeriesFullUrl(fullUrl) }, seriesMapper::map)
    }

    override fun getSeriesComics(seriesId: Int): Single<UIState<List<Comic>>> {
        return wrapResponseWithState({ apiService.getSeriesComics(seriesId) }, comicMapper::map)
    }

    override fun getSeriesEvents(seriesId: Int): Single<UIState<List<Event>>> {
        return wrapResponseWithState({ apiService.getSeriesEvents(seriesId) }, eventMapper::map)
    }

    override fun getSeriesCharacters(seriesId: Int): Single<UIState<List<Character>>> {
        return wrapResponseWithState(
            { apiService.getSeriesCharacters(seriesId) }, characterDomainMapper::map
        )
    }

    override fun getEventCharacters(eventId: Int): Single<UIState<List<Character>>> {
        return wrapResponseWithState(
            { apiService.getEventCharacters(eventId) }, characterDomainMapper::map
        )
    }


    override fun getAllSeries(): Single<UIState<List<Series>>> {
        return wrapResponseWithState({ apiService.getAllSeries() }, seriesMapper::map)
    }


    override fun getComicEvents(comicsId: Int): Single<UIState<List<Event>>> {
        return wrapResponseWithState({ apiService.getComicEvents(comicsId) }, eventMapper::map)
    }

    override fun getComicCharacters(comicsId: Int): Single<UIState<List<Character>>> {
        return wrapResponseWithState(
            { apiService.getComicCharacters(comicsId) }, characterDomainMapper::map
        )
    }

    override fun getComicSeries(comicsId: Int): Single<UIState<List<Series>>> {
        return wrapResponseWithState({ apiService.getComicSeries(comicsId) }, seriesMapper::map)
    }


    override fun getEventSeries(comicsId: Int): Single<UIState<List<Series>>> {
        return wrapResponseWithState({ apiService.getEventSeries(comicsId) }, seriesMapper::map)
    }


    override fun getSeriesCreators(creatorId: Int): Single<UIState<List<Creator>>> {
        return wrapResponseWithState(
            { apiService.getSeriesCreators(creatorId) }, creatorMapper::map
        )
    }

    override fun getComicCreators(comicsId: Int): Single<UIState<List<Creator>>> {
        return wrapResponseWithState({ apiService.getComicCreators(comicsId) }, creatorMapper::map)
    }

    override fun getCreatorEvents(creatorId: Int): Single<UIState<List<Event>>> {
        return wrapResponseWithState({ apiService.getCreatorEvents(creatorId) }, eventMapper::map)
    }

    override fun getCreatorCharacters(creatorId: Int): Single<UIState<List<Character>>> {
        return wrapResponseWithState(
            { apiService.getCreatorCharacters(creatorId) }, characterDomainMapper::map
        )
    }

    override fun getCreatorComics(creatorId: Int): Single<UIState<List<Comic>>> {
        return wrapResponseWithState({ apiService.getSeriesComics(creatorId) }, comicMapper::map)
    }

    override fun getCreatorSeries(creatorId: Int): Single<UIState<List<Series>>> {
        return wrapResponseWithState({ apiService.getCreatorSeries(creatorId) }, seriesMapper::map)
    }

    private fun <I, O> wrapResponseWithState(
        request: () -> Single<Response<BaseResponse<I>>>, mapper: (List<I>) -> List<O>
    ): Single<UIState<List<O>>> {
        return request().map {
            if (it.isSuccessful) {
                val transformedList = mapper(it.body()?.data?.results!!)
                UIState.Success(transformedList)
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
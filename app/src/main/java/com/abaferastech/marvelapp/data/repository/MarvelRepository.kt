package com.abaferastech.marvelapp.data.repository

import android.provider.CalendarContract.Events
import com.abaferastech.marvelapp.data.local.database.daos.MarvelDao
import com.abaferastech.marvelapp.data.local.database.daos.SearchDao
import com.abaferastech.marvelapp.data.local.database.entity.*
import com.abaferastech.marvelapp.data.local.database.entity.search.CharacterSearchEntity
import com.abaferastech.marvelapp.data.local.database.entity.search.ComicSearchEntity
import com.abaferastech.marvelapp.data.local.database.entity.search.EventSearchEntity
import com.abaferastech.marvelapp.data.local.database.entity.search.SeriesSearchEntity
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

    fun Comic.asComicSearchEntity() = ComicSearchEntity(
        id = id,
        title = title,
        issueNumber = issueNumber,
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
    fun Event.asEventSearchEntity() = EventSearchEntity(
        id = id,
        title = title,
        imageUri = imageUri
    )

    fun EventSearchEntity.asEvent() = Event(
        id = id,
        title = title,
        description = null,
        modified = null,
        imageUri = imageUri
    )
    fun Character.asCharacterSearchEntity() = CharacterSearchEntity(
        id = id,
        name = name,
        modified = modified,
        imageUri = imageUri
    )

    fun CharacterSearchEntity.asCharacter() = Character(
        id = id,
        name = name,
        description = null,
        modified = modified,
        imageUri = imageUri
    )
    fun Series.asSeriesSearchEntity() = SeriesSearchEntity(
        id = id,
        title = title,
        startYear= startYear,
        modified = modified,
        imageUri = imageUri
    )

    fun SeriesSearchEntity.asSeries() = Series(
        id = id,
        title = title,
        description = null,
        startYear = startYear,
        endYear = null,
        rating = null,
        modified = modified,
        imageUri = imageUri
    )
    fun Comic.asComicEntity()=ComicEntity(
             id = id,
            title = title,
            issueNumber = issueNumber,
            modified = modified,
            imageUri = imageUri,
            description = null,
            price = null,
            pageCount = null
    )

fun ComicEntity.asComics()=Comic(
        id=id,
      title=title,
    description=description ,
    issueNumber=issueNumber,
     price=price,
    pageCount= pageCount,
    modified = modified,
    imageUri=imageUri
)
    fun Series.asSeriesEntity()=SeriesEntity (
            id = id,
            title = title,
            startYear = startYear,
            modified = modified,
            imageUri = imageUri,
            description = null,
            endYear = null,
            rating = null
        )

    fun SeriesEntity.asSeries()=Series (

            id = id,
            title = title,
            description = description,
            startYear = startYear,
            endYear = endYear,
            rating = rating,
            modified = modified,
            imageUri = imageUri
        )

    fun Character.asCharacterEntity(): CharacterEntity {
        return CharacterEntity(
            id = id,
            name = name,
            description = description,
            modified = modified,
            imageUri = imageUri
        )
    }

    fun CharacterEntity.asCharacter(): Character {
        return Character(
            id = id,
            name = name,
            description = description,
            modified = modified,
            imageUri = imageUri
        )
    }
    fun Event.asEventEntity(): EventEntity {
        return EventEntity(
            id = id,
            title = title,
            modified = modified,
            imageUri = imageUri,
            description = null,

        )
    }
    fun EventEntity.asEvent(): Event {
        return Event(
            id = id,
            title = title,
            description = null,
            modified = modified,
            imageUri = imageUri
        )
    }






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

    private fun refreshSearchComics(title: String): Completable {
        return wrapResponseWithState({ apiService.searchInComics(title) }, comicMapper::map)
            .flatMapCompletable { uiState ->
                when (uiState) {
                    is UIState.Success -> {
                        val comics = uiState.data
                        Completable.fromCallable {
                            comics?.map { it.asComicSearchEntity() }?.let {
                                searchDao.insertSearchedComicList(it)
                            }
                        }
                    }
                    else -> {
                        Completable.error(Throwable("Failed to fetch comics"))
                    }
                }
            }
    }

    override fun searchInComics(title: String): Observable<List<Comic>> {
        return searchDao.getSearchedComics(title)
            .flatMap {
                if (it.isEmpty()) {
                    refreshSearchComics(title).andThen(
                        searchDao.getSearchedComics(title).map { comicSearchEntities ->
                            comicSearchEntities.map { searchEntity -> searchEntity.asComic() }
                        })
                } else {
                    Observable.just(it.map { searchEntity -> searchEntity.asComic() })
                }
            }
    }
    private fun refreshSearchEvents(title: String): Completable {
        return wrapResponseWithState({ apiService.searchInEvents(title) }, eventMapper::map)
            .flatMapCompletable { uiState ->
                if (uiState is UIState.Success) {
                    val events = uiState.data
                    Completable.fromCallable {
                        events?.map { it.asEventSearchEntity() }?.let {
                            searchDao.insertSearchedEventList(it)
                        }
                    }
                } else {
                    Completable.error(Throwable("Failed to fetch events"))
                }
            }
    }

    override fun searchInEvents(title: String): Observable<List<Event>> {
        return searchDao.getSearchedEvents(title)
            .flatMap {
                if (it.isEmpty()) {
                    refreshSearchEvents(title).andThen(
                        searchDao.getSearchedEvents(title).map { eventSearchEntities ->
                            eventSearchEntities.map { searchEntity -> searchEntity.asEvent() }
                        })
                } else {
                    Observable.just(it.map { searchEntity -> searchEntity.asEvent() })
                }
            }
    }
    private fun refreshSearchCharacters(name: String): Completable {
        return wrapResponseWithState({ apiService.searchInCharacters(name) }, characterDomainMapper::map)
            .flatMapCompletable { uiState ->
                if (uiState is UIState.Success) {
                    val characters = uiState.data
                    Completable.fromCallable {
                        characters?.map { it.asCharacterSearchEntity() }?.let {
                            searchDao.insertSearchedCharacterList(it)
                        }
                    }
                } else {
                    Completable.error(Throwable("Failed to fetch characters"))
                }
            }
    }
    override fun searchInCharacters(name: String): Observable<List<Character>> {
        return searchDao.getSearchedCharacter(name)
            .flatMap {
                if (it.isEmpty()) {
                    refreshSearchCharacters(name).andThen(
                        searchDao.getSearchedCharacter(name).map { characterSearchEntities ->
                            characterSearchEntities.map { searchEntity -> searchEntity.asCharacter() }
                        }
                    )
                } else {
                    Observable.just(it.map { searchEntity -> searchEntity.asCharacter() })
                }
            }
    }
    private fun refreshSearchSeries(title: String): Completable {
        return wrapResponseWithState({ apiService.searchInSeries(title) }, seriesMapper::map)
            .flatMapCompletable { uiState ->
                if (uiState is UIState.Success) {
                    val series = uiState.data
                    Completable.fromCallable {
                        series?.map { it.asSeriesSearchEntity() }?.let {
                            searchDao.insertSearchedSeriesList(it)
                        }
                    }
                } else {
                    Completable.error(Throwable("Failed to fetch series"))
                }
            }
    }
    override fun searchInSeries(title: String): Observable<List<Series>> {
        return searchDao.getSearchedSeries(title)
            .flatMap {
                if (it.isEmpty()) {
                    refreshSearchSeries(title).andThen(
                        searchDao.getSearchedSeries(title).map { seriesSearchEntities ->
                            seriesSearchEntities.map { searchEntity -> searchEntity.asSeries() }
                        }
                    )
                } else {
                    Observable.just(it.map { searchEntity -> searchEntity.asSeries() })
                }
            }
    }

    private fun refreshComics(): Completable {
        return wrapResponseWithState({ apiService.getAllComics() }, comicMapper::map)
            .flatMapCompletable { uiState ->
                when (uiState) {
                    is UIState.Success -> {
                        val comics = uiState.data
                        Completable.fromCallable {
                            comics?.map { it.asComicEntity() }?.let {
                                marvelDao.insertComicList(it)
                            }
                        }
                    }
                    else -> {
                        Completable.error(Throwable("Failed to fetch comics"))
                    }
                }
            }
    }


    override fun getAllComics(): Observable<List<Comic>> {
        return marvelDao.getAllComics()
            .flatMap {
                if (it.isEmpty()) {
                    refreshComics().andThen(
                        marvelDao.getAllComics().map { ComicsEntity ->
                            ComicsEntity.map { ComicEntity -> ComicEntity.asComics() }
                        }
                    )
                } else {
                    Observable.just(it.map { ComicEntity -> ComicEntity.asComics() })
                }
            }
    }
    private fun refreshSeries(): Completable {
        return wrapResponseWithState({ apiService.getAllSeries() }, seriesMapper::map)
            .flatMapCompletable { uiState ->
                when (uiState) {
                    is UIState.Success -> {
                        val series = uiState.data
                        Completable.fromCallable {
                            series?.map { it.asSeriesEntity() }?.let {
                                marvelDao.insertSeriesList(it)
                            }
                        }
                    }
                    else -> {
                        Completable.error(Throwable("Failed to fetch series"))
                    }
                }
            }
    }
    override fun getAllSeries(): Observable<List<Series>> {
        return marvelDao.getAllSeries()
            .flatMap {
                if (it.isEmpty()) {
                    refreshSeries().andThen(
                        marvelDao.getAllSeries().map { seriesEntities ->
                            seriesEntities.map { seriesEntity -> seriesEntity.asSeries() }
                        }
                    )
                } else {
                    Observable.just(it.map { seriesEntity -> seriesEntity.asSeries() })
                }
            }
    }
    private fun refreshCharacters(): Completable {
        return wrapResponseWithState({ apiService.getAllCharacters() }, characterDomainMapper::map)
            .flatMapCompletable { uiState ->
                when (uiState) {
                    is UIState.Success -> {
                        val characters = uiState.data
                        Completable.fromCallable {
                            characters?.map { it.asCharacterEntity() }?.let {
                                marvelDao.insertCharacterList(it)
                            }
                        }
                    }
                    else -> {
                        Completable.error(Throwable("Failed to fetch characters"))
                    }
                }
            }
    }


    override fun getAllCharacters(): Observable<List<Character>> {
        return marvelDao.getAllCharacters()
            .flatMap {
                if (it.isEmpty()) {
                    refreshCharacters().andThen(
                        marvelDao.getAllCharacters().map { characterEntities ->
                            characterEntities.map { characterEntity -> characterEntity.asCharacter() }
                        }
                    )
                } else {
                    Observable.just(it.map { characterEntity -> characterEntity.asCharacter() })
                }
            }
    }
    private fun refreshEvents(): Completable {
        return wrapResponseWithState({ apiService.getAllEvents() }, eventMapper::map)
            .flatMapCompletable { uiState ->
                when (uiState) {
                    is UIState.Success -> {
                        val events = uiState.data
                        Completable.fromCallable {
                            events?.map { it.asEventEntity() }?.let {
                                marvelDao.insertEventList(it)
                            }
                        }
                    }
                    else -> {
                        Completable.error(Throwable("Failed to fetch events"))
                    }
                }
            }
    }

    override fun getAllEvents(): Observable<List<Event>> {
        return marvelDao.getAllEvents()
            .flatMap {
                if (it.isEmpty()) {
                    refreshEvents().andThen(
                        marvelDao.getAllEvents().map { eventEntities ->
                            eventEntities.map { eventEntity -> eventEntity.asEvent() }
                        }
                    )
                } else {
                    Observable.just(it.map { eventEntity -> eventEntity.asEvent() })
                }
            }
    }







//    override fun searchInComics(title: String): Observable<List<Comic>> {
//        return searchDao.getSearchedComics(title)
//            .flatMap {
//                if (it.isEmpty()) {
//                    refreshSearchComics(title).andThen(
//                        searchDao.getSearchedComics(title).map { comicSearchEntities ->
//                            comicSearchEntities.map { searchEntity -> searchEntity.asComic() }
//                        })
//                } else {
//                    Observable.just(it.map { searchEntity -> searchEntity.asComic() })
//                }
//            }
//    }




//    override fun searchInCharacters(query: String): Single<UIState<List<Character>>> {
//        TODO("Not yet implemented")
//    }

    /*private fun refreshSearchedCharacters(name: String): Completable {
        return wrapResponseWithState({ apiService.searchInCharacters(name) }, characterDomainMapper::map)
            .flatMapCompletable { uiState ->
                if (uiState is UIState.Success) {
                    val characters = uiState.data
                    Completable.fromCallable {
                        characters?.map { it.as }?.let {
                            searchDao.insertSearchedComicList(it)
                        }
                    }
                } else {
                    Completable.error(Throwable("Failed to fetch comics"))
                }
            }
    }*/

    /*override fun searchInCharacters(query: String): Observable<List<Character>> {
        return searchDao.getSearchedCharacter(query)
            .flatMap {
                if (it.isEmpty()) {
                    refreshSearchedCharacters(title).andThen(
                        searchDao.getSearchedComics(title).map { comicSearchEntities ->
                            comicSearchEntities.map { searchEntity -> searchEntity.asComic() }
                        })
                } else {
                    Observable.just(it.map { searchEntity -> searchEntity.asComic() })
                }
            }
    }*/

//    override fun searchInEvents(query: String): Single<UIState<List<Event>>> {
//        return wrapResponseWithState({ apiService.searchInEvents(query) }, eventMapper::map)
//    }

//    override fun searchInSeries(query: String): Single<UIState<List<Series>>> {
//
//        return wrapResponseWithState({ apiService.searchInSeries(query) }, seriesMapper::map)
//    }


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

//    override fun getAllEvents(): Single<UIState<List<Event>>> {
//        return wrapResponseWithState({ apiService.getAllEvents() }, eventMapper::map)
//    }

//    override fun getAllCharacters(): Single<UIState<List<Character>>> {
//        return wrapResponseWithState({ apiService.getAllCharacters() }, characterDomainMapper::map)
//
//    }


//    override fun getAllComics(): Single<UIState<List<Comic>>> {
//        return wrapResponseWithState({ apiService.getAllComics() }, comicMapper::map)
//    }

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


//    override fun getAllSeries(): Single<UIState<List<Series>>> {
//        return wrapResponseWithState({ apiService.getAllSeries() }, seriesMapper::map)
//    }


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


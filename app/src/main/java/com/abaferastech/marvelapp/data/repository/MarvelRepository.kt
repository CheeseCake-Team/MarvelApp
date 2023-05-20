package com.abaferastech.marvelapp.data.repository

import android.annotation.SuppressLint
import android.util.Log
import com.abaferastech.marvelapp.data.local.database.daos.FavouriteDao
import com.abaferastech.marvelapp.data.local.database.daos.MarvelDao
import com.abaferastech.marvelapp.data.local.database.daos.SearchDao
import com.abaferastech.marvelapp.data.local.database.entity.CharacterEntity
import com.abaferastech.marvelapp.data.local.database.entity.ComicEntity
import com.abaferastech.marvelapp.data.local.database.entity.SearchQueryEntity
import com.abaferastech.marvelapp.data.local.database.entity.SeriesEntity
import com.abaferastech.marvelapp.data.local.searchMappers.DtoToEntity.CharacterSearchDtoMapper
import com.abaferastech.marvelapp.data.local.searchMappers.DtoToEntity.ComicSearchDtolMapper
import com.abaferastech.marvelapp.data.local.searchMappers.DtoToEntity.EventSearchDtoMapper
import com.abaferastech.marvelapp.data.local.searchMappers.DtoToEntity.SeriesSearchDtoMapper
import com.abaferastech.marvelapp.data.local.searchMappers.EntityToModel.CharacterSearchEntityMapper
import com.abaferastech.marvelapp.data.local.searchMappers.EntityToModel.ComicSearchEntityMapper
import com.abaferastech.marvelapp.data.local.searchMappers.EntityToModel.EventSearchEntityMapper
import com.abaferastech.marvelapp.data.local.searchMappers.EntityToModel.SeriesSearchEntityMapper
import com.abaferastech.marvelapp.data.remote.MarvelApiService
import com.abaferastech.marvelapp.data.remote.response.BaseResponse
import com.abaferastech.marvelapp.domain.mapper.CharacterDomainMapper
import com.abaferastech.marvelapp.domain.mapper.ComicDominMapper
import com.abaferastech.marvelapp.domain.mapper.CreatorMapper
import com.abaferastech.marvelapp.domain.mapper.DtoToEntity.CharacterDtoMapper
import com.abaferastech.marvelapp.domain.mapper.DtoToEntity.ComicDtoMapper
import com.abaferastech.marvelapp.domain.mapper.DtoToEntity.SeriesDtoMapper
import com.abaferastech.marvelapp.domain.mapper.EntityToModel.CharacterEntityMapper
import com.abaferastech.marvelapp.domain.mapper.EntityToModel.ComicEntityMapper
import com.abaferastech.marvelapp.domain.mapper.EntityToModel.SeriesEntityMapper
import com.abaferastech.marvelapp.domain.mapper.EventMapper
import com.abaferastech.marvelapp.domain.mapper.SeriesMapper
import com.abaferastech.marvelapp.domain.models.Character
import com.abaferastech.marvelapp.domain.models.Comic
import com.abaferastech.marvelapp.domain.models.Creator
import com.abaferastech.marvelapp.domain.models.Event
import com.abaferastech.marvelapp.domain.models.SearchQuery
import com.abaferastech.marvelapp.domain.models.Series
import com.abaferastech.marvelapp.ui.model.UIState
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class MarvelRepository @Inject constructor(
    private val apiService: MarvelApiService,
    private val comicMapper: ComicDominMapper,
    private val seriesMapper: SeriesMapper,
    private val creatorMapper: CreatorMapper,
    private val eventMapper: EventMapper,
    private val creatorMapper: CreatorMapper,
    private val characterDomainMapper: CharacterDomainMapper,
    private val searchDao: SearchDao,
    private val marvelDao: MarvelDao,
    private val eventSearchDtoMapper: EventSearchDtoMapper,
    private val characterSearchDtoMapper: CharacterSearchDtoMapper,
    private val seriesSearchDtoMapper: SeriesSearchDtoMapper,
    private val comicDtoMapper: ComicDtoMapper,
    private val seriesDtoMapper: SeriesDtoMapper,
    private val characterDtoMapper: CharacterDtoMapper,
    private val comicSearchEntityMapper: ComicSearchEntityMapper,
    private val eventSearchEntityMapper: EventSearchEntityMapper,
    private val comicEntityMapper: ComicEntityMapper,
    private val characterSearchEntityMapper: CharacterSearchEntityMapper,
    private val seriesSearchEntityMapper: SeriesSearchEntityMapper,
    private val seriesEntityMapper: SeriesEntityMapper,
    private val characterEntityMapper: CharacterEntityMapper
) : IMarvelRepository {
    private val favouriteDao: FavouriteDao,

    ) : IMarvelRepository {


    fun SearchQuery.asSearchQueryEntity() = SearchQueryEntity(id, searchQuery)
    fun SearchQueryEntity.asSearchQuery() = SearchQuery(id, searchQuery)


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
    val comicSearchMapper= ComicSearchDtolMapper()
    private fun refreshSearchComics(title: String): Completable {
        return wrapResponseWithState(
            { apiService.searchInComics(title) },
            comicSearchMapper::map
        ).flatMapCompletable { uiState ->
            when (uiState) {
                is UIState.Success -> {
                    val comics = uiState.data
                    Completable.fromCallable {
                        comics?.let {
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

    private fun refreshSearchEvents(title: String): Completable {
        return wrapResponseWithState({ apiService.searchInEvents(title) }, eventSearchDtoMapper::map)
            .flatMapCompletable { uiState ->
                if (uiState is UIState.Success) {
                    val events = uiState.data
                    Completable.fromCallable {
                        events?.let {
                            searchDao.insertSearchedEventList(it)
                        }
                    }
                } else {
                    Completable.error(Throwable("Failed to fetch events"))
                }
            }
    }
    private fun refreshSearchCharacters(name: String): Completable {
        return wrapResponseWithState(
            { apiService.searchInCharacters(name) }, characterSearchDtoMapper::map
        ).flatMapCompletable { uiState ->
            if (uiState is UIState.Success) {
                val characters = uiState.data
                Completable.fromCallable {
                    characters?.let {
                        searchDao.insertSearchedCharacterList(it)
                    }
                }
            } else {
                Completable.error(Throwable("Failed to fetch characters"))
            }
        }
    }
    private fun refreshSearchSeries(title: String): Completable {
        return wrapResponseWithState(
            { apiService.searchInSeries(title) },
            seriesSearchDtoMapper::map
        ).flatMapCompletable { uiState ->
            if (uiState is UIState.Success) {
                val series = uiState.data
                Completable.fromCallable {
                    series?.let {
                        searchDao.insertSearchedSeriesList(it)
                    }
                }
            } else {
                Completable.error(Throwable("Failed to fetch series"))
            }
        }
    }

    private fun refreshComics(): Completable {
        return wrapResponseWithState(
            { apiService.getAllComics() },
            comicDtoMapper::map
        ).flatMapCompletable { uiState ->
            when (uiState) {
                is UIState.Success -> {
                    val comics = uiState.data
                    Completable.fromCallable {
                        comics?.let {
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
    private fun refreshSeries(): Completable {
        return wrapResponseWithState(
            { apiService.getAllSeries() },
            seriesDtoMapper::map
        ).flatMapCompletable { uiState ->
            when (uiState) {
                is UIState.Success -> {
                    val series = uiState.data
                    Completable.fromCallable {
                        series?.let {
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
    private fun refreshCharacters(): Completable {
        return wrapResponseWithState({ apiService.getAllCharacters() }, characterDtoMapper::map)
            .flatMapCompletable { uiState ->
                when (uiState) {
                    is UIState.Success -> {
                        marvelDao.deleteAllCharacters()
                        val characters = uiState.data
                        Completable.fromCallable {
                            characters?.let {
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
    override fun searchInComics(title: String): Observable<List<Comic>> {
        return searchDao.getSearchedComics(title).flatMap { comicSearchEntities ->
            if (comicSearchEntities.isEmpty()) {
                refreshSearchComics(title).andThen(searchDao.getSearchedComics(title))
                    .map { comicSearchEntityMapper.map(it) }
            } else {
                Observable.just(comicSearchEntityMapper.map(comicSearchEntities))
            }
        }
    }

    override fun searchInEvents(query: String): Observable<List<Event>> {
        return searchDao.getSearchedEvents(query).flatMap { eventSearchEntities ->
            if (eventSearchEntities.isEmpty()) {
                refreshSearchEvents(query).andThen(searchDao.getSearchedEvents(query))
                    .map { eventSearchEntityMapper.map(it) }
            } else {
                Observable.just(eventSearchEntityMapper.map(eventSearchEntities))
            }
        }
    }


//    override fun searchInEvents(query: String): Observable<List<Event>> {
//        return searchDao.getSearchedEvents(query).flatMap {
//            if (it.isEmpty()) {
//                refreshSearchEvents(query).andThen(searchDao.getSearchedEvents(query)
//                    .map { eventSearchEntities ->
//                        eventSearchEntities.map { searchEntity -> searchEntity.asEvent() }
//                    })
//            } else {
//                Observable.just(it.map { searchEntity -> searchEntity.asEvent() })
//            }
//        }
//    }

    override fun searchInCharacters(name: String): Observable<List<Character>> {
        return searchDao.getSearchedCharacter(name).flatMap { characterSearchEntities ->
            if (characterSearchEntities.isEmpty()) {
                refreshSearchCharacters(name).andThen(searchDao.getSearchedCharacter(name))
                    .map { characterSearchEntityMapper.map(it) }
            } else {
                Observable.just(characterSearchEntityMapper.map(characterSearchEntities))
            }
        }
    }


    override fun searchInSeries(title: String): Observable<List<Series>> {
        return searchDao.getSearchedSeries(title).flatMap { seriesSearchEntities ->
            if (seriesSearchEntities.isEmpty()) {
                refreshSearchSeries(title).andThen(searchDao.getSearchedSeries(title))
                    .map { SeriesSearchEntityMapper().map(it) }
            } else {
                Observable.just(SeriesSearchEntityMapper().map(seriesSearchEntities))
            }
        }
    }

//    override fun searchInSeries(title: String): Observable<List<Series>> {
//        return searchDao.getSearchedSeries(title).flatMap {
//            if (it.isEmpty()) {
//                refreshSearchSeries(title).andThen(searchDao.getSearchedSeries(title)
//                    .map { seriesSearchEntities ->
//                        seriesSearchEntities.map { searchEntity -> searchEntity.asSeries() }
//                    })
//            } else {
//                Observable.just(it.map { searchEntity -> searchEntity.asSeries() })
//            }
//        }
//    }


    override fun getAllComics(): Observable<UIState<List<Comic>>> {
        return marvelDao.getAllComics().flatMap {
            if (it.isEmpty()) {
                refreshComics().andThen(marvelDao.getAllComics().map { comicsEntities ->
                    UIState.Success(comicEntityMapper.map(comicsEntities))
                })
            } else {
                Observable.just(UIState.Success(comicEntityMapper.map(it)))
            }
        }
    }

    override fun getAllSeries(): Observable<UIState<List<Series>>> {
        return marvelDao.getAllSeries().flatMap {
            if (it.isEmpty()) {
                refreshSeries().andThen(marvelDao.getAllSeries().map { seriesEntities ->
                    UIState.Success(seriesEntityMapper.map(seriesEntities))
                })
            } else {
                Observable.just(UIState.Success(seriesEntityMapper.map(it)))
            }
        }
    }



    override fun getAllCharacters(): Observable<UIState<List<Character>>> {
        return marvelDao.getAllCharacters().flatMap {
            if (it.isEmpty()) {
                refreshCharacters().andThen(marvelDao.getAllCharacters()
                    .map { characterEntities ->
                        UIState.Success(characterEntityMapper.map(characterEntities))
                    })
            } else {
                Observable.just(UIState.Success(characterEntityMapper.map(it)))
            }
        }
    }



    override fun getAllEvents(): Single<UIState<List<Event>>> {
        return wrapResponseWithState(
            { apiService.getAllEvents() },
            eventMapper::map
        )
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
//        return wrapResponseWithState(
//            { apiService.getSingleCharacter(characterId) }, characterDomainMapper::map
//        ).mapListToSingleItem()
            val character = favouriteDao.getCharacterByIdNullable(characterId)
            return if (character == null) {
                Log.d("MAMO", "getSingleCharacter: from api ${character}")
                wrapResponseWithState(
                    { apiService.getSingleCharacter(characterId) }, characterDomainMapper::map
                ).mapListToSingleItem()
            } else {
                Log.d("MAMO", "getSingleCharacter: from room ${character}")
                Single.just(UIState.Success(character.asDomainModel()))
            }
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
        }.onErrorReturn { t ->
            Log.e("mujtaba", "1")
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

//    fun getCachedCharacters() {
//        characterDao.getAllCashedCharacters()
//    }
//    fun getCachedComics() {
//        comicDao.getCachedComics()
//    }

    fun insertCharacter(character: Character) {
        favouriteDao.insertCharacter(character.asEntityModel())
    }

    fun getAllCashedCharacters(): Single<List<Character>> {
        return favouriteDao.getAllCashedCharacters().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { list -> list.map { it.asDomainModel() } }
    }

    fun insertComic(comic: Comic) {
        favouriteDao.insertComic(comic.asEntityModel()).subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io()).subscribe()
    }

    @SuppressLint("CheckResult")
    fun deleteComic(comic: Comic) {
        favouriteDao.deleteComic(comic.asEntityModel()).subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io()).subscribe()
    }

    fun getAllCashedComic(): Single<List<Comic>> {
        return favouriteDao.getCachedComics().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { list -> list.map { it.asDomainModel() } }
    }

    fun deleteCharacter(character: Character) {
        favouriteDao.deleteCharacter(character.asEntityModel())
    }


    fun Character.asEntityModel(): CharacterFavouriteEntity {
        return CharacterFavouriteEntity(
            id = this.id,
            name = this.name,
            description = this.description,
            modified = this.modified,
            imageUri = this.imageUri,
            isFavourite = this.isFavourite
        )
    }

    fun CharacterFavouriteEntity.asDomainModel(): Character {
        return Character(
            id = this.id,
            name = this.name,
            description = this.description,
            modified = this.modified,
            imageUri = this.imageUri,
            isFavourite = this.isFavourite
        )
    }

    fun Comic.asEntityModel(): ComicFavouriteEntity {
        return ComicFavouriteEntity(
            id = this.id,
            title = this.title,
            description = this.description,
            modified = this.modified,
            imageUri = this.imageUri,
            price = this.price,
            pageCount = this.pageCount,
            issueNumber = this.issueNumber,
            isFavourite = this.isFavourite
        )
    }

    fun ComicFavouriteEntity.asDomainModel(): Comic {
        return Comic(
            id = this.id,
            title = this.title,
            description = this.description,
            modified = this.modified,
            imageUri = this.imageUri,
            price = this.price,
            pageCount = this.pageCount,
            issueNumber = this.issueNumber,
            isFavourite = this.isFavourite
        )
    }

}

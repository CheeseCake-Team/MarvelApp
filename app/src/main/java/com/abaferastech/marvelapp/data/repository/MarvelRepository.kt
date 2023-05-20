package com.abaferastech.marvelapp.data.repository

import android.annotation.SuppressLint
import com.abaferastech.marvelapp.data.local.database.daos.FavouriteDao
import com.abaferastech.marvelapp.data.local.database.daos.MarvelDao
import com.abaferastech.marvelapp.data.local.database.daos.SearchDao
import com.abaferastech.marvelapp.data.local.database.entity.favourite.CharacterFavouriteEntity
import com.abaferastech.marvelapp.data.local.database.entity.favourite.ComicFavouriteEntity
import com.abaferastech.marvelapp.data.local.database.entity.favourite.SeriesFavouriteEntity
import com.abaferastech.marvelapp.data.local.database.entity.search.SearchQueryEntity
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
    private val characterEntityMapper: CharacterEntityMapper,
    private val favouriteDao: FavouriteDao,
    private val comicSearchMapper: ComicSearchDtolMapper
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


    private fun refreshSearchComics(title: String): Completable {
        return refreshData(
            { apiService.searchInComics(title) },
            comicSearchMapper::map,
            searchDao::insertSearchedComicList,
            "Failed to fetch comics"
        )
    }




    private fun refreshSearchEvents(title: String): Completable {
        return refreshData(
            { apiService.searchInEvents(title) },
            eventSearchDtoMapper::map,
            searchDao::insertSearchedEventList,
            "Failed to fetch events"
        )
    }

    private fun refreshSearchCharacters(name: String): Completable {
        return refreshData(
            { apiService.searchInCharacters(name) },
            characterSearchDtoMapper::map,
            searchDao::insertSearchedCharacterList,
            "Failed to fetch characters"
        )
    }

    private fun refreshSearchSeries(title: String): Completable {
        return refreshData(
            { apiService.searchInSeries(title) },
            seriesSearchDtoMapper::map,
            searchDao::insertSearchedSeriesList,
            "Failed to fetch series"
        )
    }


    private fun refreshComics(): Completable {
        return refreshData(
            { apiService.getAllComics() },
            comicDtoMapper::map,
            marvelDao::insertComicList,
            "Failed to fetch comics"
        )
    }

    private fun refreshSeries(): Completable {
        return refreshData(
            { apiService.getAllSeries() },
            seriesDtoMapper::map,
            marvelDao::insertSeriesList,
            "Failed to fetch series"
        )
    }

    private fun refreshCharacters(): Completable {
        return refreshData(
            { apiService.getAllCharacters() },
            characterDtoMapper::map,
            marvelDao::insertCharacterList,
            "Failed to fetch characters"
        )
    }
    private fun <I, O> refreshData(
        apiCall: () -> Single<Response<BaseResponse<I>>>,
        mapper: (List<I>) -> List<O>,
        insertData: (List<O>) -> Unit,
        errorMessage: String
    ): Completable {
        return wrapResponseWithState(apiCall, mapper)
            .flatMapCompletable { uiState ->
                when (uiState) {
                    is UIState.Success -> {
                        val data = uiState.data
                        Completable.fromCallable {
                            insertData(data!!)
                        }
                    }
                    else -> Completable.error(Throwable(errorMessage))
                }
            }
    }

    private fun <T, R> refreshAndFetchData(
        refreshData: () -> Completable,
        fetchData: () -> Observable<List<T>>,
        mapData: (List<T>) -> List<R>
    ): Observable<List<R>> {
        return fetchData().flatMap { dataEntities ->
            if (dataEntities.isEmpty()) {
                refreshData().andThen(fetchData().map(mapData))
            } else {
                Observable.just(mapData(dataEntities))
            }
        }
    }

    override fun searchInComics(title: String): Observable<List<Comic>> {
        return refreshAndFetchData(
            { refreshSearchComics(title) },
            { searchDao.getSearchedComics(title) },
            comicSearchEntityMapper::map
        )
    }

    override fun searchInEvents(query: String): Observable<List<Event>> {
        return refreshAndFetchData(
            { refreshSearchEvents(query) },
            { searchDao.getSearchedEvents(query) },
            eventSearchEntityMapper::map
        )
    }

    override fun searchInCharacters(name: String): Observable<List<Character>> {
        return refreshAndFetchData(
            { refreshSearchCharacters(name) },
            { searchDao.getSearchedCharacter(name) },
            characterSearchEntityMapper::map
        )
    }

    override fun searchInSeries(title: String): Observable<List<Series>> {
        return refreshAndFetchData(
            { refreshSearchSeries(title) },
            { searchDao.getSearchedSeries(title) },
            seriesSearchEntityMapper::map
        )
    }

    override fun getAllComics(): Observable<UIState<List<Comic>>> {
        return refreshAndFetchData(
            { refreshComics() },
            { marvelDao.getAllComics() },
            comicEntityMapper::map
        ).map { UIState.Success(it) }
    }

    override fun getAllSeries(): Observable<UIState<List<Series>>> {
        return refreshAndFetchData(
            { refreshSeries() },
            { marvelDao.getAllSeries() },
            seriesEntityMapper::map
        ).map { UIState.Success(it) }
    }

    override fun getAllCharacters(): Observable<UIState<List<Character>>> {
        return refreshAndFetchData(
            { refreshCharacters() },
            { marvelDao.getAllCharacters() },
            characterEntityMapper::map
        ).map { UIState.Success(it) }
    }

    override fun getAllEvents(): Single<UIState<List<Event>>> {
        return wrapResponseWithState(
            { apiService.getAllEvents() },
            eventMapper::map
        )
    }


    override fun getSingleCharacter(characterId: Int): Single<UIState<Character>> {
        val character = favouriteDao.getCharacterByIdNullable(characterId)
        return if (character == null) {
            wrapResponseWithState(
                { apiService.getSingleCharacter(characterId) }, characterDomainMapper::map
            ).mapListToSingleItem()
        } else {
            Single.just(UIState.Success(character.asDomainModel()))
        }
    }


    override fun getSingleEvent(eventId: Int): Single<UIState<Event>> {
        return wrapResponseWithState(
            { apiService.getEventsById(eventId) }, eventMapper::map
        ).mapListToSingleItem()
    }


    override fun getSingleSeries(seriesId: Int): Single<UIState<Series>> {
        return wrapResponseWithState(
            { apiService.getSingleSeries(seriesId) }, seriesMapper::map
        ).mapListToSingleItem()

    }

    override fun getSingleComic(comicId: Int): Single<UIState<Comic>> {
        return wrapResponseWithState(
            { apiService.getSingleComic(comicId) }, comicMapper::map
        ).mapListToSingleItem()
    }

    override fun getSingleCreator(creatorId: Int): Single<UIState<Creator>> {
        return wrapResponseWithState(
            { apiService.getSingleCreator(creatorId) }, creatorMapper::map
        ).mapListToSingleItem()
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

    override fun getComicEvents(comicId: Int): Single<UIState<List<Event>>> {
        return wrapResponseWithState({ apiService.getComicEvents(comicId) }, eventMapper::map)
    }

    override fun getComicCharacters(comicId: Int): Single<UIState<List<Character>>> {
        return wrapResponseWithState(
            { apiService.getComicCharacters(comicId) }, characterDomainMapper::map
        )
    }

    override fun getComicSeries(comicId: Int): Single<UIState<List<Series>>> {
        return wrapResponseWithState({ apiService.getComicSeries(comicId) }, seriesMapper::map)
    }


    override fun getEventSeries(eventId: Int): Single<UIState<List<Series>>> {
        return wrapResponseWithState({ apiService.getEventSeries(eventId) }, seriesMapper::map)
    }


    override fun getSeriesCreators(creatorId: Int): Single<UIState<List<Creator>>> {
        return wrapResponseWithState(
            { apiService.getSeriesCreators(creatorId) }, creatorMapper::map
        )
    }

    override fun getComicCreators(comicId: Int): Single<UIState<List<Creator>>> {
        return wrapResponseWithState({ apiService.getComicCreators(comicId) }, creatorMapper::map)
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


    fun insertCharacter(character: Character) {
        favouriteDao.insertCharacter(character.asEntityModel()).subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io()).subscribe()
    }

    fun insertComic(comic: Comic) {
        favouriteDao.insertComic(comic.asEntityModel()).subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io()).subscribe()
    }

    fun getAllCashedCharacters(): Single<List<Character>> {
        return favouriteDao.getAllCashedCharacters()
            .map { list -> list.map { it.asDomainModel() } }
    }

    fun getAllCashedComic(): Single<List<Comic>> {
        return favouriteDao.getCachedComics()
            .map { list -> list.map { it.asDomainModel() } }.subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
    }


    @SuppressLint("CheckResult")
    fun deleteComic(comic: Comic) {
        favouriteDao.deleteComic(comic.asEntityModel()).subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io()).subscribe()
    }


    fun insertSeries(series: Series) {
        favouriteDao.insertSeries(series.asEntityModel()).subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io()).subscribe()
    }

    fun deleteSeries(series: Series) {
        favouriteDao.deleteSeries(series.asEntityModel()).subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io()).subscribe()
    }

    fun getAllCashedSeries(): Single<List<Series>> {
        return favouriteDao.getAllCashedSeries().subscribeOn(Schedulers.io())
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

    fun Series.asEntityModel(): SeriesFavouriteEntity {
        return SeriesFavouriteEntity(
            id = this.id,
            title = this.title,
            description = this.description,
            rating = this.rating,
            imageUri = this.imageUri,
            startYear = this.startYear,
            endYear = this.endYear,
            modified = this.modified
        )
    }

    fun SeriesFavouriteEntity.asDomainModel(): Series {
        return Series(
            id = this.id,
            title = this.title,
            description = this.description,
            rating = this.rating,
            imageUri = this.imageUri,
            startYear = this.startYear,
            endYear = this.endYear,
            isFavourite = this.isFavourite,
            modified = this.modified
        )
    }

}

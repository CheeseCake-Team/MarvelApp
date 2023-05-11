package com.abaferastech.marvelapp.data.network

import com.abaferastech.marvelapp.data.model.*
import com.abaferastech.marvelapp.data.model.response.MarvelBaseResponse
import com.abaferastech.marvelapp.data.model.result.*
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelApiService {
    @GET("events/{eventId}/comics")
    fun getEventComics(@Path("eventId") eventId: Int) : Single<Response<MarvelBaseResponse<Comics>>>
    @GET("events/{eventId}/characters")
    fun getEventCharacters(@Path("eventId") eventId: Int) : Single<Response<MarvelBaseResponse<Characters>>>

    @GET("events")
    fun getAllEvents(): Single<Response<MarvelBaseResponse<Events>>>

    @GET("comics")
    fun searchInComics (@Query("titleStartsWith") searchQuery: String): Single<Response<MarvelBaseResponse<Comics>>>

    @GET("series")
    fun searchInSeries (@Query("titleStartsWith") searchQuery: String): Single<Response<MarvelBaseResponse<Series>>>

    @GET("events")
    fun searchInEvents (@Query("nameStartsWith") searchQuery: String): Single<Response<MarvelBaseResponse<Events>>>

    @GET("characters")
    fun searchInCharacters (@Query("nameStartsWith") searchQuery: String): Single<Response<MarvelBaseResponse<Characters>>>

    @GET("characters/{characterId}")
    fun getSingleCharacter(@Path("characterId") characterId: Int): Single<Response<MarvelBaseResponse<Characters>>>
    @GET("events/{eventsId}")
    fun getEventsById(@Path("eventsId") eventsId: Int): Single<Response<MarvelBaseResponse<Events>>>

    @GET("characters")
    fun getAllCharacters(): Single<Response<MarvelBaseResponse<Characters>>>

    @GET("characters/{characterId}/events")
    fun getCharacterEvents(@Path("characterId") seriesId: Int): Single<Response<MarvelBaseResponse<Events>>>

    @GET("characters/{characterId}/comics")
    fun getCharacterComics(@Path("characterId") seriesId: Int): Single<Response<MarvelBaseResponse<Comics>>>

    @GET("characters/{characterId}/series")
    fun getCharacterSeries(@Path("characterId") seriesId: Int): Single<Response<MarvelBaseResponse<Series>>>

    @GET("series")
    fun getAllSeries(): Single<Response<MarvelBaseResponse<Series>>>

    @GET("series/{seriesId}")
    fun getSingleSeries(@Path("seriesId") seriesId: Int): Single<Response<MarvelBaseResponse<Series>>>

    @GET("{fullUrl}")
    fun getSeriesFullUrl(@Path(value = "fullUrl", encoded = true) fullUrl: String): Single<Response<MarvelBaseResponse<Series>>>

    @GET("series/{seriesId}/events")
    fun getSeriesEvents(@Path("seriesId") characterId: Int): Single<Response<MarvelBaseResponse<Events>>>

    @GET("series/{seriesId}/characters")
    fun getSeriesCharacters(@Path("seriesId") characterId: Int): Single<Response<MarvelBaseResponse<Characters>>>

    @GET("series/{seriesId}/comics")
    fun getSeriesComics(@Path("seriesId") characterId: Int): Single<Response<MarvelBaseResponse<Comics>>>

    @GET("series/{seriesId}/creators")
    fun getSeriesCreators(@Path("seriesId") characterId: Int): Single<Response<MarvelBaseResponse<Creators>>>

    @GET("series/{seriesId}/stories")
    fun getSeriesStories(@Path("seriesId") characterId: Int): Single<Response<MarvelBaseResponse<Stories>>>

    @GET("comics")
    fun getAllComics(): Single<Response<MarvelBaseResponse<Comics>>>

    @GET("comics/{comicsId}")
    fun getSingleComic(@Path("comicsId") comicsId: Int): Single<Response<MarvelBaseResponse<Comics>>>

    @GET("comics/{comicsId}/events")
    fun getComicEvents(@Path("comicsId") comicsId: Int): Single<Response<MarvelBaseResponse<Events>>>

    @GET("comics/{comicsId}/characters")
    fun getComicCharacters(@Path("comicsId") comicsId: Int): Single<Response<MarvelBaseResponse<Characters>>>

    @GET("comics/{comicsId}/series")
    fun getComicSeries(@Path("comicsId") comicsId: Int): Single<Response<MarvelBaseResponse<Series>>>

    @GET("comics/{comicsId}/creators")
    fun getComicCreators(@Path("comicsId") comicsId: Int): Single<Response<MarvelBaseResponse<Creators>>>

    @GET("comics/{comicsId}/stories")
    fun getComicStories(@Path("comicsId") comicsId: Int): Single<Response<MarvelBaseResponse<Stories>>>

    @GET("creators")
    fun getAllCreators(): Single<Response<MarvelBaseResponse<Creators>>>

    @GET("creators/{creatorId}")
    fun getSingleCreator(@Path("creatorId") creatorId: Int): Single<Response<MarvelBaseResponse<Creators>>>

    @GET("creators/{creatorId}/events")
    fun getCreatorEvents(@Path("creatorId") creatorId: Int): Single<Response<MarvelBaseResponse<Events>>>

    @GET("creators/{creatorId}/characters")
    fun getCreatorCharacters(@Path("creatorId") creatorId: Int): Single<Response<MarvelBaseResponse<Characters>>>

    @GET("creators/{creatorId}/comics")
    fun getCreatorComics(@Path("creatorId") creatorId: Int): Single<Response<MarvelBaseResponse<Comics>>>

    @GET("creators/{creatorId}/series")
    fun getCreatorSeries(@Path("creatorId") creatorId: Int): Single<Response<MarvelBaseResponse<Series>>>

    @GET("creators/{creatorId}/stories")
    fun getCreatorStories(@Path("creatorId") creatorId: Int): Single<Response<MarvelBaseResponse<Stories>>>

    @GET("stories")
    fun getAllStories(): Single<Response<MarvelBaseResponse<Stories>>>

}


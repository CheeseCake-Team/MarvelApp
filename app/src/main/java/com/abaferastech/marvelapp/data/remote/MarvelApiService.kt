package com.abaferastech.marvelapp.data.remote

import com.abaferastech.marvelapp.data.remote.response.BaseResponse
import com.abaferastech.marvelapp.data.remote.response.CharacterDTO
import com.abaferastech.marvelapp.data.remote.response.ComicDTO
import com.abaferastech.marvelapp.data.remote.response.CreatorDTO
import com.abaferastech.marvelapp.data.remote.response.EventDTO
import com.abaferastech.marvelapp.data.remote.response.SeriesDTO
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelApiService {
    @GET("events/{eventId}/comics")
    fun getEventComics(@Path("eventId") eventId: Int): Single<Response<BaseResponse<ComicDTO>>>

    @GET("events/{eventId}/characters")
    fun getEventCharacters(@Path("eventId") eventId: Int): Single<Response<BaseResponse<CharacterDTO>>>

    @GET("events")
    fun getAllEvents(): Single<Response<BaseResponse<EventDTO>>>

    @GET("comics")
    fun searchInComics(@Query("titleStartsWith") searchQuery: String): Single<Response<BaseResponse<ComicDTO>>>

    @GET("series")
    fun searchInSeries(@Query("titleStartsWith") searchQuery: String): Single<Response<BaseResponse<SeriesDTO>>>

    @GET("events")
    fun searchInEvents(@Query("nameStartsWith") searchQuery: String): Single<Response<BaseResponse<EventDTO>>>

    @GET("characters")
    fun searchInCharacters(@Query("nameStartsWith") searchQuery: String): Single<Response<BaseResponse<CharacterDTO>>>

    @GET("characters/{characterId}")
    fun getSingleCharacter(@Path("characterId") characterId: Int): Single<Response<BaseResponse<CharacterDTO>>>

    @GET("events/{eventsId}")
    fun getEventsById(@Path("eventsId") eventsId: Int): Single<Response<BaseResponse<EventDTO>>>


    @GET("characters")
    fun getAllCharacters(): Single<Response<BaseResponse<CharacterDTO>>>

    @GET("events/{eventsId}/series")
    fun getEventSeries(@Path("eventsId") eventsId: Int): Single<Response<BaseResponse<SeriesDTO>>>

    @GET("characters/{characterId}/events")
    fun getCharacterEvents(@Path("characterId") seriesId: Int): Single<Response<BaseResponse<EventDTO>>>

    @GET("characters/{characterId}/comics")
    fun getCharacterComics(@Path("characterId") seriesId: Int): Single<Response<BaseResponse<ComicDTO>>>

    @GET("characters/{characterId}/series")
    fun getCharacterSeries(@Path("characterId") seriesId: Int): Single<Response<BaseResponse<SeriesDTO>>>

    @GET("series")
    fun getAllSeries(): Single<Response<BaseResponse<SeriesDTO>>>

    @GET("series/{seriesId}")
    fun getSingleSeries(@Path("seriesId") seriesId: Int): Single<Response<BaseResponse<SeriesDTO>>>

    @GET("{fullUrl}")
    fun getSeriesFullUrl(
        @Path(
            value = "fullUrl",
            encoded = true
        ) fullUrl: String
    ): Single<Response<BaseResponse<SeriesDTO>>>

    @GET("series/{seriesId}/events")
    fun getSeriesEvents(@Path("seriesId") characterId: Int): Single<Response<BaseResponse<EventDTO>>>

    @GET("series/{seriesId}/characters")
    fun getSeriesCharacters(@Path("seriesId") characterId: Int): Single<Response<BaseResponse<CharacterDTO>>>

    @GET("series/{seriesId}/comics")
    fun getSeriesComics(@Path("seriesId") characterId: Int): Single<Response<BaseResponse<ComicDTO>>>

    @GET("series/{seriesId}/creators")
    fun getSeriesCreators(@Path("seriesId") characterId: Int): Single<Response<BaseResponse<CreatorDTO>>>


    @GET("comics")
    fun getAllComics(): Single<Response<BaseResponse<ComicDTO>>>

    @GET("comics/{comicsId}")
    fun getSingleComic(@Path("comicsId") comicsId: Int): Single<Response<BaseResponse<ComicDTO>>>

    @GET("comics/{comicsId}/events")
    fun getComicEvents(@Path("comicsId") comicsId: Int): Single<Response<BaseResponse<EventDTO>>>

    @GET("comics/{comicsId}/characters")
    fun getComicCharacters(@Path("comicsId") comicsId: Int): Single<Response<BaseResponse<CharacterDTO>>>

    @GET("comics/{comicsId}/series")
    fun getComicSeries(@Path("comicsId") comicsId: Int): Single<Response<BaseResponse<SeriesDTO>>>

    @GET("comics/{comicsId}/creators")
    fun getComicCreators(@Path("comicsId") comicsId: Int): Single<Response<BaseResponse<CreatorDTO>>>


    @GET("creators")
    fun getAllCreators(): Single<Response<BaseResponse<CreatorDTO>>>

    @GET("creators/{creatorId}")
    fun getSingleCreator(@Path("creatorId") creatorId: Int): Single<Response<BaseResponse<CreatorDTO>>>

    @GET("creators/{creatorId}/events")
    fun getCreatorEvents(@Path("creatorId") creatorId: Int): Single<Response<BaseResponse<EventDTO>>>

    @GET("creators/{creatorId}/characters")
    fun getCreatorCharacters(@Path("creatorId") creatorId: Int): Single<Response<BaseResponse<CharacterDTO>>>

    @GET("creators/{creatorId}/comics")
    fun getCreatorComics(@Path("creatorId") creatorId: Int): Single<Response<BaseResponse<ComicDTO>>>

    @GET("creators/{creatorId}/series")
    fun getCreatorSeries(@Path("creatorId") creatorId: Int): Single<Response<BaseResponse<SeriesDTO>>>

}


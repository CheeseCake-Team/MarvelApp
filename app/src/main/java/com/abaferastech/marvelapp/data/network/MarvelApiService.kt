package com.abaferastech.marvelapp.data.network

import com.abaferastech.marvelapp.data.model.*
import com.abaferastech.marvelapp.data.model.response.MarvelResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelApiService {


    @GET("events/{eventId}/comics")
    fun getEventComics(@Path("eventId") eventId: Int) : Single<Response<MarvelResponse<Comics>>>

    @GET("events")
    fun getAllEvents(): Single<Response<MarvelResponse<Events>>>

    @GET("comics")
    fun searchInComics (@Query("title") searchQuery: String): Single<Response<MarvelResponse<Comics>>>

    @GET("characters")
    fun searchInCharacters (@Query("name") searchQuery: String): Single<Response<MarvelResponse<Characters>>>

    @GET("characters/{characterId}")
    fun getSingleCharacter(@Path("characterId") characterId: Int): Single<Response<MarvelResponse<Characters>>>
    @GET("events/{eventsId}")
    fun getEventsById(@Path("eventsId") eventsId: Int): Single<Response<MarvelResponse<Events>>>

    @GET("characters")
    fun getAllCharacters(): Single<Response<MarvelResponse<Characters>>>

    @GET("series")
    fun getAllSeries(): Single<Response<MarvelResponse<Series>>>

    @GET("series/{seriesId}")
    fun getSingleSeries(@Path("seriesId") seriesId: Int): Single<Response<MarvelResponse<Series>>>

    @GET("{fullUrl}")
    fun getSeriesFullUrl(@Path(value = "fullUrl", encoded = true) fullUrl: String): Single<Response<MarvelResponse<Series>>>

    @GET("series/{seriesId}/events")
    fun getSeriesEvents(@Path("seriesId") characterId: Int): Single<Response<MarvelResponse<Events>>>

    @GET("series/{seriesId}/character")
    fun getSeriesCharacters(@Path("seriesId") characterId: Int): Single<Response<MarvelResponse<Characters>>>

    @GET("series/{seriesId}/comics")
    fun getSeriesComics(@Path("seriesId") characterId: Int): Single<Response<MarvelResponse<Comics>>>

    @GET("series/{seriesId}/creators")
    fun getSeriesCreators(@Path("seriesId") characterId: Int): Single<Response<MarvelResponse<Creators>>>

    @GET("series/{seriesId}/stories")
    fun getSeriesStories(@Path("seriesId") characterId: Int): Single<Response<MarvelResponse<Stories>>>

    @GET("comics")
    fun getAllComics(): Single<Response<MarvelResponse<Comics>>>

    @GET("comics/{comicsId}")
    fun getSingleComic(@Path("comicsId") comicsId: Int): Single<Response<MarvelResponse<Comics>>>

    @GET("comics/{comicsId}/events")
    fun getComicEvents(@Path("comicsId") comicsId: Int): Single<Response<MarvelResponse<Events>>>

    @GET("comics/{comicsId}/characters")
    fun getComicCharacters(@Path("comicsId") comicsId: Int): Single<Response<MarvelResponse<Characters>>>

    @GET("comics/{comicsId}/series")
    fun getComicSeries(@Path("comicsId") comicsId: Int): Single<Response<MarvelResponse<Series>>>

    @GET("comics/{comicsId}/creators")
    fun getComicCreators(@Path("comicsId") comicsId: Int): Single<Response<MarvelResponse<Creators>>>

    @GET("comics/{comicsId}/stories")
    fun getComicStories(@Path("comicsId") comicsId: Int): Single<Response<MarvelResponse<Stories>>>

    @GET("creators")
    fun getAllCreators(): Single<Response<MarvelResponse<Creators>>>

    @GET("creators/{creatorId}")
    fun getSingleCreator(@Path("creatorId") creatorId: Int): Single<Response<MarvelResponse<Creators>>>

    @GET("creators/{creatorId}/events")
    fun getCreatorEvents(@Path("creatorId") creatorId: Int): Single<Response<MarvelResponse<Events>>>

    @GET("creators/{creatorId}/characters")
    fun getCreatorCharacters(@Path("creatorId") creatorId: Int): Single<Response<MarvelResponse<Characters>>>

    @GET("creators/{creatorId}/comics")
    fun getCreatorComics(@Path("creatorId") creatorId: Int): Single<Response<MarvelResponse<Comics>>>

    @GET("creators/{creatorId}/series")
    fun getCreatorSeries(@Path("creatorId") creatorId: Int): Single<Response<MarvelResponse<Series>>>

    @GET("creators/{creatorId}/stories")
    fun getCreatorStories(@Path("creatorId") creatorId: Int): Single<Response<MarvelResponse<Stories>>>

    @GET("stories")
    fun getAllStories(): Single<Response<MarvelResponse<Stories>>>

    @GET("stories/{storyId}")
    fun getSingleStory(@Path("storyId") storyId: Int): Single<Response<MarvelResponse<Stories>>>

    @GET("stories/{storyId}/events")
    fun getStoryEvents(@Path("storyId") storyId: Int): Single<Response<MarvelResponse<Events>>>

    @GET("stories/{storyId}/characters")
    fun getStoryCharacters(@Path("storyId") storyId: Int): Single<Response<MarvelResponse<Characters>>>

    @GET("stories/{storyId}/comics")
    fun getStoryComics(@Path("storyId") storyId: Int): Single<Response<MarvelResponse<Comics>>>

    @GET("stories/{storyId}/creators")
    fun getStoryCreators(@Path("storyId") storyId: Int): Single<Response<MarvelResponse<Creators>>>

    @GET("stories/{storyId}/series")
    fun getStorySeries(@Path("storyId") storyId: Int): Single<Response<MarvelResponse<Series>>>

}


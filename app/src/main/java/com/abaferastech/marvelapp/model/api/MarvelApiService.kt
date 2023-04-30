package com.abaferastech.marvelapp.model.api

import com.abaferastech.marvelapp.model.models.Character
import com.abaferastech.marvelapp.model.models.Comic
import com.abaferastech.marvelapp.model.models.Creator
import com.abaferastech.marvelapp.model.models.Data
import com.abaferastech.marvelapp.model.models.Event
import com.abaferastech.marvelapp.model.models.Series
import com.abaferastech.marvelapp.model.models.Story
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MarvelApiService {

    @GET("/v1/public/characters")
    fun getCharacters(): Call<Data<Character>>

    @GET("/v1/public/characters/{characterId}")
    fun getCharacterById(@Path("characterId") characterId: Int): Call<Data<Character>>

    @GET("/v1/public/characters/{characterId}/comics")
    fun getComicsByCharacterId(@Path("characterId") characterId: Int): Call<Data<Comic>>

    @GET("/v1/public/characters/{characterId}/events")
    fun getEventsByCharacterId(@Path("characterId") characterId: Int): Call<Data<Event>>

    @GET("/v1/public/characters/{characterId}/series")
    fun getSeriesByCharacterId(@Path("characterId") characterId: Int): Call<Data<Series>>

    @GET("/v1/public/characters/{characterId}/stories")
    fun getStoriesByCharacterId(@Path("characterId") characterId: Int): Call<Data<Story>>

    @GET("/v1/public/comics")
    fun getComics(): Call<Data<Comic>>

    @GET("/v1/public/comics/{comicId}")
    fun getComicById(@Path("comicId") comicId: Int): Call<Data<Comic>>

    @GET("/v1/public/comics/{comicId}/characters")
    fun getCharactersByComicId(@Path("comicId") comicId: Int): Call<Data<Character>>

    @GET("/v1/public/comics/{comicId}/creators")
    fun getCreatorsByComicId(@Path("comicId") comicId: Int): Call<Data<Creator>>

    @GET("/v1/public/comics/{comicId}/events")
    fun getEventsByComicId(@Path("comicId") comicId: Int): Call<Data<Event>>

    @GET("/v1/public/comics/{comicId}/stories")
    fun getStoriesByComicId(@Path("comicId") comicId: Int): Call<Data<Story>>

    @GET("/v1/public/creators")
    fun getCreators(): Call<Data<Creator>>

    @GET("/v1/public/creators/{creatorId}")
    fun getCreatorById(@Path("creatorId") creatorId: Int): Call<Data<Character>>

    @GET("/v1/public/creators/{creatorId}/comics")
    fun getComicsByCreatorId(@Path("creatorId") creatorId: Int): Call<Data<Comic>>

    @GET("/v1/public/creators/{creatorId}/events")
    fun getEventsByCreatorId(@Path("creatorId") creatorId: Int): Call<Data<Event>>

    @GET("/v1/public/creators/{creatorId}/series")
    fun getSeriesByCreatorId(@Path("creatorId") creatorId: Int): Call<Data<Series>>

    @GET("/v1/public/creators/{creatorId}/stories")
    fun getStoriesByCreatorId(@Path("creatorId") creatorId: Int): Call<Data<Story>>
    @GET("/v1/public/events")
    fun getEvents(): Call<Data<Event>>

    @GET("/v1/public/events/{eventId}")
    fun getEventById(@Path("eventId") eventId: Int): Call<Data<Event>>

    @GET("/v1/public/events/{eventId}/characters")
    fun getCharactersByEventId(@Path("eventId") eventId: Int): Call<Data<Character>>

    @GET("/v1/public/events/{eventId}/comics")
    fun getComicsByEventId(@Path("eventId") eventId: Int): Call<Data<Comic>>

    @GET("/v1/public/events/{eventId}/creators")
    fun getCreatorsByEventId(@Path("eventId") eventId: Int): Call<Data<Creator>>

    @GET("/v1/public/events/{eventId}/series")
    fun getSeriesByEventId(@Path("eventId") eventId: Int): Call<Data<Series>>

    @GET("/v1/public/events/{eventId}/stories")
    fun getStoriesByEventId(@Path("eventId") eventId: Int): Call<Data<Story>>

    @GET("/v1/public/series")
    fun getSeries(): Call<Data<Series>>

    @GET("/v1/public/series/{seriesId}")
    fun getSeriesById(@Path("seriesId") seriesId: Int): Call<Data<Series>>

    @GET("/v1/public/series/{seriesId}/characters")
    fun getCharactersBySeriesId(@Path("seriesId") seriesId: Int): Call<Data<Character>>

    @GET("/v1/public/series/{seriesId}/comics")
    fun getComicsBySeriesId(@Path("seriesId") seriesId: Int): Call<Data<Comic>>

    @GET("/v1/public/series/{seriesId}/creators")
    fun getCreatorsBySeriesId(@Path("seriesId") seriesId: Int): Call<Data<Creator>>

    @GET("/v1/public/series/{seriesId}/events")
    fun getEventsBySeriesId(@Path("seriesId") seriesId: Int): Data<Event>

    @GET("/v1/public/series/{seriesId}/stories")
    fun getStoriesBySeriesId(@Path("seriesId") seriesId: Int): Call<Data<Story>>

    @GET("/v1/public/stories")
    fun getStories(): Call<Data<Story>>

    @GET("/v1/public/stories/{storyId}")
    fun getStoryById(@Path("storyId") storyId: Int): Call<Data<Story>>

    @GET("/v1/public/stories/{storyId}/characters")
    fun getCharactersByStoryId(@Path("storyId") storyId: Int): Call<Data<Character>>

    @GET("/v1/public/stories/{storyId}/comics")
    fun getComicsByStoryId(@Path("storyId") storyId: Int): Call<Data<Comic>>

    @GET("/v1/public/stories/{storyId}/creators")
    fun getCreatorsByStoryId(@Path("storyId") storyId: Int): Call<Data<Creator>>

    @GET("/v1/public/stories/{storyId}/events")
    fun getEventsByStoryId(@Path("storyId") storyId: Int): Call<Data<Event>>

    @GET("/v1/public/stories/{storyId}/series")
    fun getSeriesByStoryId(@Path("storyId") storyId: Int): Call<Data<Series>>
}
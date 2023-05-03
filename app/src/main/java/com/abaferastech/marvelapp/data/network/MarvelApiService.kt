package com.abaferastech.marvelapp.data.network

import com.abaferastech.marvelapp.data.model.*
import com.abaferastech.marvelapp.data.model.response.MarvelResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface MarvelApiService {


    @GET("series")
    fun getAllSeries(): Single<Response<MarvelResponse<Series>>>

    @GET("series/{seriesId}")
    fun getSpecificSeries(@Path("seriesId") seriesId: Int): Single<Response<MarvelResponse<Series>>>

    @GET("series/{seriesId}/events")
    fun getEventsRelatedToSeries(@Path("seriesId") characterId: Int): Single<Response<MarvelResponse<Events>>>

    @GET("series/{seriesId}/character")
    fun getCharactersRelatedToSeries(@Path("seriesId") characterId: Int): Single<Response<MarvelResponse<Characters>>>

    @GET("series/{seriesId}/comics")
    fun getComicsRelatedToSeries(@Path("seriesId") characterId: Int): Single<Response<MarvelResponse<Comics>>>

    @GET("series/{seriesId}/creators")
    fun getCreatorsRelatedToSeries(@Path("seriesId") characterId: Int): Single<Response<MarvelResponse<Creators>>>

    @GET("series/{seriesId}/stories")
    fun getStoriesRelatedToSeries(@Path("seriesId") characterId: Int): Single<Response<MarvelResponse<Stories>>>


    @GET("comics")
    fun getAllComics(): Single<Response<MarvelResponse<Comics>>>

    @GET("comics/{comicsId}")
    fun getSpecificComics(@Path("comicsId") comicsId: Int): Single<Response<MarvelResponse<Comics>>>

    @GET("comics/{comicsId}/events")
    fun getEventsRelatedToComics(@Path("comicsId") comicsId: Int): Single<Response<MarvelResponse<Events>>>

    @GET("comics/{comicsId}/character")
    fun getCharactersRelatedToComics(@Path("comicsId") comicsId: Int): Single<Response<MarvelResponse<Characters>>>

    @GET("comics/{comicsId}/series")
    fun getSeriesRelatedToComics(@Path("comicsId") comicsId: Int): Single<Response<MarvelResponse<Series>>>

    @GET("comics/{comicsId}/creators")
    fun getCreatorsRelatedToComics(@Path("comicsId") comicsId: Int): Single<Response<MarvelResponse<Creators>>>

    @GET("series/{comicsId}/stories")
    fun getStoriesRelatedToComics(@Path("comicsId") comicsId: Int): Single<Response<MarvelResponse<Stories>>>


    @GET("creators")
    fun getAllCreators(): Single<Response<MarvelResponse<Creators>>>

    @GET("creators/{creatorsId}")
    fun getSpecificCreators(@Path("creatorsId") creatorsId: Int): Single<Response<MarvelResponse<Creators>>>

    @GET("creators/{creatorsId}/events")
    fun getEventsRelatedToCreators(@Path("creatorsId") creatorsId: Int): Single<Response<MarvelResponse<Events>>>

    @GET("creators/{creatorsId}/character")
    fun getCharactersRelatedToCreators(@Path("creatorsId") creatorsId: Int): Single<Response<MarvelResponse<Characters>>>

    @GET("creators/{creatorsId}/comics")
    fun getComicsRelatedToCreators(@Path("creatorsId") creatorsId: Int): Single<Response<MarvelResponse<Comics>>>

    @GET("creators/{creatorsId}/creators")
    fun getSeriesRelatedToCreators(@Path("creatorsId") creatorsId: Int): Single<Response<MarvelResponse<Series>>>


    @GET("series/{creatorsId}/stories")
    fun getStoriesRelatedToCreators(@Path("creatorsId") creatorsId: Int): Single<Response<MarvelResponse<Stories>>>



    @GET("stories")
    fun getAllStories(): Single<Response<MarvelResponse<Stories>>>

    @GET("stories/{storiesId}")
    fun getSpecificStories(@Path("storiesId") storiesId: Int): Single<Response<MarvelResponse<Stories>>>

    @GET("stories/{storiesId}/events")
    fun getEventsRelatedToStories(@Path("storiesId") storiesId: Int): Single<Response<MarvelResponse<Events>>>

    @GET("stories/{storiesId}/character")
    fun getCharactersRelatedToStories(@Path("storiesId") storiesId: Int): Single<Response<MarvelResponse<Characters>>>

    @GET("stories/{storiesId}/comics")
    fun getComicsRelatedToStories(@Path("storiesId") storiesId: Int): Single<Response<MarvelResponse<Comics>>>

    @GET("stories/{storiesId}/creators")
    fun getCreatorsRelatedToStories(@Path("storiesId") storiesId: Int): Single<Response<MarvelResponse<Creators>>>

    @GET("stories/{creatorsId}/series")
    fun getSeriesRelatedToStories(@Path("creatorsId") creatorsId: Int): Single<Response<MarvelResponse<Series>>>

}


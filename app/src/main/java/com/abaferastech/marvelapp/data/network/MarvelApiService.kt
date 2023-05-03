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
    fun getSingleSeries(@Path("seriesId") seriesId: Int): Single<Response<MarvelResponse<Series>>>

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
    fun getSpecificComics(@Path("comicsId") comicsId: Int): Single<Response<MarvelResponse<Comics>>>

    @GET("comics/{comicsId}/events")
    fun getEventsComics(@Path("comicsId") comicsId: Int): Single<Response<MarvelResponse<Events>>>

    @GET("comics/{comicsId}/character")
    fun getCharactersComics(@Path("comicsId") comicsId: Int): Single<Response<MarvelResponse<Characters>>>

    @GET("comics/{comicsId}/series")
    fun getSeriesdComics(@Path("comicsId") comicsId: Int): Single<Response<MarvelResponse<Series>>>

    @GET("comics/{comicsId}/creators")
    fun getCreatorsComics(@Path("comicsId") comicsId: Int): Single<Response<MarvelResponse<Creators>>>

    @GET("series/{comicsId}/stories")
    fun getStoriesComics(@Path("comicsId") comicsId: Int): Single<Response<MarvelResponse<Stories>>>


    @GET("creators")
    fun getAllCreators(): Single<Response<MarvelResponse<Creators>>>

    @GET("creators/{creatorsId}")
    fun getSpecificCreators(@Path("creatorsId") creatorsId: Int): Single<Response<MarvelResponse<Creators>>>

    @GET("creators/{creatorsId}/events")
    fun getEventsCreators(@Path("creatorsId") creatorsId: Int): Single<Response<MarvelResponse<Events>>>

    @GET("creators/{creatorsId}/character")
    fun getCharactersCreators(@Path("creatorsId") creatorsId: Int): Single<Response<MarvelResponse<Characters>>>

    @GET("creators/{creatorsId}/comics")
    fun getComicsCreators(@Path("creatorsId") creatorsId: Int): Single<Response<MarvelResponse<Comics>>>

    @GET("creators/{creatorsId}/creators")
    fun getSeriesdCreators(@Path("creatorsId") creatorsId: Int): Single<Response<MarvelResponse<Series>>>


    @GET("series/{creatorsId}/stories")
    fun getStoriesCreators(@Path("creatorsId") creatorsId: Int): Single<Response<MarvelResponse<Stories>>>



    @GET("stories")
    fun getAllStories(): Single<Response<MarvelResponse<Stories>>>

    @GET("stories/{storiesId}")
    fun getSpecificStories(@Path("storiesId") storiesId: Int): Single<Response<MarvelResponse<Stories>>>

    @GET("stories/{storiesId}/events")
    fun getEventsStories(@Path("storiesId") storiesId: Int): Single<Response<MarvelResponse<Events>>>

    @GET("stories/{storiesId}/character")
    fun getCharactersStories(@Path("storiesId") storiesId: Int): Single<Response<MarvelResponse<Characters>>>

    @GET("stories/{storiesId}/comics")
    fun getComicsStories(@Path("storiesId") storiesId: Int): Single<Response<MarvelResponse<Comics>>>

    @GET("stories/{storiesId}/creators")
    fun getCreatorsStories(@Path("storiesId") storiesId: Int): Single<Response<MarvelResponse<Creators>>>

    @GET("stories/{creatorsId}/series")
    fun getSeriessStories(@Path("creatorsId") creatorsId: Int): Single<Response<MarvelResponse<Series>>>

}


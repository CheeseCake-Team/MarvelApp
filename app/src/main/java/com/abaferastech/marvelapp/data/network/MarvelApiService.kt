package com.abaferastech.marvelapp.data.network

import com.abaferastech.marvelapp.data.model.*
import com.abaferastech.marvelapp.data.model.response.MarvelResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface MarvelApiService {

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


}


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
    fun getEventsOfSeries(@Path("eventsId") characterId: Int): Single<Response<MarvelResponse<Events>>>

    @GET("series/{seriesId}/character")
    fun getCharactersOfSeries(@Path("characterId") characterId: Int): Single<Response<MarvelResponse<Characters>>>

    @GET("series/{seriesId}/comics")
    fun getComicsOfSeries(@Path("comicsId") characterId: Int): Single<Response<MarvelResponse<Comics>>>

    @GET("series/{seriesId}/creators")
    fun getCreatorsOfSeries(@Path("creatorsId") characterId: Int): Single<Response<MarvelResponse<Creators>>>



}


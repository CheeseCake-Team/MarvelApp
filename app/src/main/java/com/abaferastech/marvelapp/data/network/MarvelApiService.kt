package com.abaferastech.marvelapp.data.network

import com.abaferastech.marvelapp.data.model.Series
import com.abaferastech.marvelapp.data.model.response.MarvelResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET


interface MarvelApiService {

    @GET("series")
    fun getSeries(): Single<Response<MarvelResponse<Series>>>
}


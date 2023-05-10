package com.abaferastech.marvelapp.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object MarvelAPI {
    private const val BASE_URL = "http://gateway.marvel.com/v1/public/"

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    private val client = OkHttpClient
        .Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(MarvelInterceptor())
        .build()


    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: MarvelApiService = retrofit.create(MarvelApiService::class.java)
}
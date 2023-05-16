package com.abaferastech.marvelapp.data.remote

import com.abaferastech.marvelapp.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object MarvelAPI {

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    private val client = OkHttpClient
        .Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(AuthInterceptor(MessageDigest()))
        .build()


    private val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: MarvelApiService = retrofit.create(MarvelApiService::class.java)
}
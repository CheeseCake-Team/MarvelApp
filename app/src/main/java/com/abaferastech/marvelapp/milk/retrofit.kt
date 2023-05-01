package com.abaferastech.marvelapp.milk

import com.abaferastech.marvelapp.data.models.ApiResponse
import com.abaferastech.marvelapp.data.models.Story
import io.reactivex.rxjava3.core.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface MarvelApiService {
    @GET("stories")
    fun getStories(): Single<ApiResponse>
}


object MarvelAPI {
    private const val BASE_URL = "https://gateway.marvel.com/v1/public/"

    private val interceptor = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    private val client = OkHttpClient
        .Builder()
        .addInterceptor(interceptor)
        .addInterceptor(MarvelQueryParametersInterceptor())
        .build()


    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: MarvelApiService = retrofit.create(MarvelApiService::class.java)
}

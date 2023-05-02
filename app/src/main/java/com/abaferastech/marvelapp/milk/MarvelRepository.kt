package com.abaferastech.marvelapp.milk

import android.util.Log
import com.abaferastech.marvelapp.data.models.ApiResponse
import com.abaferastech.marvelapp.data.models.Series
import com.abaferastech.marvelapp.data.models.Story
import com.abaferastech.marvelapp.data.models.state.State
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response

class MarvelRepository {


    fun getMarvelSeries(): Single<State<MarvelResponse>> {
        return wrapperWithState { MarvelAPI.apiService.getSeries() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    private fun <T> wrapperWithState(function: () -> Single<Response<T>>): Single<State<T>> {
        return function().map {
            if (it.isSuccessful) {
                State.Success(it.body())
            } else {
                State.Error(it.message())
            }
        }
    }


}
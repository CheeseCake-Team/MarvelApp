package com.abaferastech.marvelapp.utils

import android.util.Log
import com.abaferastech.marvelapp.data.model.state.State
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response


fun <T> wrapWithState(function: () -> Single<Response<T>>): Single<State<T>> {
    return function().map {
        if (it.isSuccessful) {
            State.Success(it.body())
        } else {
            State.Error(it.message())
        }
    }.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}
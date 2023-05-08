package com.abaferastech.marvelapp.data.repository

import android.util.Log
import com.abaferastech.marvelapp.data.model.state.State
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response


fun <T> wrapWithState(function: () -> Single<Response<T>>): Single<State<T>> {
    return function().map {
        if (it.isSuccessful) {
            Log.i("netanrtnaarnartntrn", "wrapWithState: ${it.body()}")
            State.Success(it.body())
        } else {
            Log.i("netanrtnaarnartntrn", "wrapWithState: ${it.message()}")
            State.Error(it.message())
        }
    }.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}
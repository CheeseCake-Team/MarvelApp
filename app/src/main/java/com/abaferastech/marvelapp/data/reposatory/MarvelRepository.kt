package com.abaferastech.marvelapp.data.reposatory

import android.util.Log
import com.abaferastech.marvelapp.data.model.Series
import com.abaferastech.marvelapp.data.network.MarvelAPI
import com.abaferastech.marvelapp.data.model.response.MarvelResponse
import com.abaferastech.marvelapp.data.model.state.State
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response

class MarvelRepository {

//    fun getAllMarvelSeries(): Single<State<MarvelResponse<Series>>> {
//        return wrapperWithState { MarvelAPI.apiService.getAllSeries() }
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//    }

    fun getSpecificMarvelSeries(seriesId: Int): Single<State<MarvelResponse<Series>>> {
        return wrapperWithState { MarvelAPI.apiService.getSpecificSeries(seriesId) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    private fun <T> wrapperWithState(function: () -> Single<Response<T>>): Single<State<T>> {
        return function().map {
            if (it.isSuccessful) {
                Log.i("mahamehoooooooo", "wrapperWithState: $it")
                State.Success(it.body())
            } else {
                Log.i("mahamehoooooooo", "wrapperWithState: $it")
                State.Error(it.message())
            }
        }
    }


}
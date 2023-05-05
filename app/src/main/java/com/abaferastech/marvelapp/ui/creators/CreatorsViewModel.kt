package com.abaferastech.marvelapp.ui.creators

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.abaferastech.marvelapp.data.model.Creators
import com.abaferastech.marvelapp.data.model.Series
import com.abaferastech.marvelapp.data.model.Stories
import com.abaferastech.marvelapp.data.model.response.MarvelResponse
import com.abaferastech.marvelapp.data.model.state.State
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.ui.base.BaseViewModel
import io.reactivex.rxjava3.kotlin.addTo

class CreatorsViewModel: BaseViewModel(),SeriesInterActionListener {
    private val repository = MarvelRepository()

    private val _stories = MutableLiveData<List<Series>>()
    val stories : LiveData<List<Series>> get() = _stories

    private val _creator = MutableLiveData<Creators>()
    val creator : LiveData<Creators> get() = _creator
    init {
        getMarvelCreator()
    }
    private fun getMarvelCreator() {
        repository.getSingleCreator(15)
            .subscribe(::onSuccess, ::onError)
            .addTo(compositeDisposable)

    }

//    private fun onSuccess(state : State<MarvelResponse<Creators>>){
//        when(state){
//            is State.Error -> TODO()
//            State.Loading -> TODO()
//            is State.Success -> {
//                _creator.postValue(state.toData()?.data?.results?.first())
//
//                _creator.value?.id.let {
//                    repository.getCreatorSeries(it)
//                        .subscribe(::onCreatorSeriesSuccess)
//                }
//            }
//        }
//    }
    private fun onCreatorSeriesSuccess() {}

    private fun onError(e: Throwable) {
        Log.e("MarvelAPI", "getMarvelStories() - Error: ${e.message}")
    }


}
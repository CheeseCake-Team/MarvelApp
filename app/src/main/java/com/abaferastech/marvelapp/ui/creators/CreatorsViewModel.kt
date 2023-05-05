package com.abaferastech.marvelapp.ui.creators

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.abaferastech.marvelapp.data.model.Creators
import com.abaferastech.marvelapp.data.model.Series
import com.abaferastech.marvelapp.data.model.response.MarvelResponse
import com.abaferastech.marvelapp.data.model.state.State
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.ui.base.BaseViewModel

class CreatorsViewModel: BaseViewModel(),CreatorsInteractionListener {
    private val repository = MarvelRepository()

    private val _creator = MutableLiveData<Creators>()
    val creator : LiveData<Creators> get() = _creator
    init {
        getMarvelCreator()
    }
    private fun getMarvelCreator() {
        repository.getSingleCreator(15)
            .subscribe(::onSuccess, ::onError)

    }

    private fun onSuccess(state : State<MarvelResponse<Creators>>){
        when(state){
            is State.Error -> TODO()
            State.Loading -> TODO()
            is State.Success -> _creator.postValue(state.toData()?.data?.results?.first())
        }
    }

    private fun onError(e: Throwable) {
        Log.e("MarvelAPI", "getMarvelStories() - Error: ${e.message}")
    }

    override fun onClickCreator(creator: Creators) {
        TODO("Not yet implemented")
    }
}
package com.abaferastech.marvelapp.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.abaferastech.marvelapp.data.model.Stories
import com.abaferastech.marvelapp.data.model.response.MarvelResponse
import com.abaferastech.marvelapp.data.model.state.State
import com.abaferastech.marvelapp.data.repository.MarvelRepository

class MarvelViewModel : ViewModel() {
    private val repository = MarvelRepository()
    private val _stories = MutableLiveData<Stories>()
    val stories: LiveData<Stories> get() = _stories


    init {
        getMarvelStories()
    }

    @SuppressLint("CheckResult")
    private fun getMarvelStories() {
        repository.getSingleStory(15)
            .subscribe(::onSuccess, ::onError)


    }

    private fun onSuccess(state: State<MarvelResponse<Stories>>) {
        when (state) {
            is State.Error -> TODO()
            State.Loading -> TODO()
            is State.Success -> {
                _stories.postValue(state.toData()?.data?.results?.first())
            }
        }
    }

    private fun onError(e: Throwable) {
        Log.e("MarvelAPI", "getMarvelStories() - Error: ${e.message}")
    }
}

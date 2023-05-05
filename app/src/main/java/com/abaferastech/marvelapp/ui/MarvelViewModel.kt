package com.abaferastech.marvelapp.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.abaferastech.marvelapp.data.model.Comics
import com.abaferastech.marvelapp.data.model.Stories
import com.abaferastech.marvelapp.data.model.response.MarvelResponse
import com.abaferastech.marvelapp.data.model.state.State
import com.abaferastech.marvelapp.data.repository.MarvelRepository

class MarvelViewModel : ViewModel() {
    private val repository = MarvelRepository()
    private val _stories = MutableLiveData<List<Comics>>()
    val stories: LiveData<List<Comics>> get() = _stories


    init {
        getMarvelStories()
    }

    @SuppressLint("CheckResult")
    private fun getMarvelStories() {
        repository.getAllComics()
            .subscribe(::onSuccess, ::onError)


    }

    private fun onSuccess(state: State<MarvelResponse<Comics>>) {
        when (state) {
            is State.Error -> Log.e("Mujtaba", "error")
            State.Loading -> Log.e("Mujtaba", "Loading")
            is State.Success -> {
                _stories.postValue(state.toData()?.data?.results)
                Log.e("Mujtaba", state.toData()?.data?.results.toString())
            }
        }
    }

    private fun onError(e: Throwable) {
        Log.e("MarvelAPI", "getMarvelStories() - Error: ${e.message}")
    }
}

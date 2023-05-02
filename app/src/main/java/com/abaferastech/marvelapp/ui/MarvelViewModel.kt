package com.abaferastech.marvelapp.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.abaferastech.marvelapp.data.reposatory.MarvelRepository
import com.abaferastech.marvelapp.data.model.Series

class MarvelViewModel : ViewModel() {
    private val repository = MarvelRepository()

    val series = MutableLiveData<List<Series>>()

    init {
        getMarvelStories()
        Log.d("aliiiiiiii", "error.message.toString()")

    }

    private fun getMarvelStories() {
        repository.getMarvelSeries()
            .subscribe({ response ->
                Log.d("aliiiiiiii", response.toData()!!.data.results.toString())
                series.postValue(response.toData()!!.data.results)
            }, { message ->
                Log.d("aliiiiiiii", "$message")
            })
    }

}

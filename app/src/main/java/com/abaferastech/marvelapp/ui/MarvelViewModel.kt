package com.abaferastech.marvelapp.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.data.model.Series

class MarvelViewModel : ViewModel() {
    private val repository = MarvelRepository()

    val series = MutableLiveData<Series>()

    init {
        getMarvelStories()
        Log.d("aliiiiiiii", "")

    }

    private fun getMarvelStories() {
        repository.getSingleSeries(15)
            .subscribe({ response ->
                Log.d("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh", response.toData()!!.data.results.first().toString())
                series.postValue(response.toData()!!.data.results.first())
            }, { message ->
                Log.d("aliiiiiiii", "$message")
            })
    }

}

package com.abaferastech.marvelapp.milk

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.abaferastech.marvelapp.data.models.Story
import retrofit2.Response
import java.security.MessageDigest
import java.time.Instant

class MarvelViewModel : ViewModel() {
    private val repository = MarvelRepository()

    val stories = MutableLiveData<List<Story>>()
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
                Log.d("aliiiiiiii", message.toString())
            })
    }


}

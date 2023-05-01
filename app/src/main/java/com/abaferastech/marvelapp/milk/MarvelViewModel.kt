package com.abaferastech.marvelapp.milk

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.abaferastech.marvelapp.BuildConfig
import com.abaferastech.marvelapp.data.models.Story
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import java.security.MessageDigest
import java.time.Instant

class MarvelViewModel : ViewModel(){
    val stories = MutableLiveData<List<Story>>()


    init {
        getMarvelStories()
        Log.d("aliiiiiiii","error.message.toString()")

    }


    private fun getMarvelStories() {

        val result = MarvelAPI.apiService.getStories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response ->
                    stories.postValue(response.data.results)
                    Log.d("aliiiiiiii",response.data.results.toString())

                },
                { error ->
                    Log.d("aliiiiiiii",error.message.toString())
                }
            )
    }




}

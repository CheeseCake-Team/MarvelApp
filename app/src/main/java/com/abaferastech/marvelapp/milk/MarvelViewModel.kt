package com.abaferastech.marvelapp.milk

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
    val stories = MutableLiveData<Story>()


    init {
        getMarvelStories()
        Log.d("aliiiiiiii","error.message.toString()")

    }

    private fun getMarvelStories() {
        MarvelAPI.apiService.getStories()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    stories.postValue(result)
                },
                { error ->
                    Log.d("aliiiiiiii",error.message.toString())
                }
            )
    }

//    private fun generateHash(): String {
//        val timestamp = Instant.now().epochSecond.toString()
//        val privateKey = BuildConfig.pKey
//        val publicKey = BuildConfig.lKey
//        val inputString = timestamp + privateKey + publicKey
//        val md = MessageDigest.getInstance("MD5").digest(inputString.toByteArray())
//        return md.joinToString("") { "%02x".format(it) }
//    }


}

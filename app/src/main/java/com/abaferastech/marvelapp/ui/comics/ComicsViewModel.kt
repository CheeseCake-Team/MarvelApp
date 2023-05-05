package com.abaferastech.marvelapp.ui.comics

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.abaferastech.marvelapp.data.model.Comics
import com.abaferastech.marvelapp.data.model.response.MarvelResponse
import com.abaferastech.marvelapp.data.model.state.State
import com.abaferastech.marvelapp.ui.base.BaseViewModel

class ComicsViewModel() :BaseViewModel(),ComicsInteractionListener {

   private val _comics = MutableLiveData<List<Comics>>()
    val comics:LiveData<List<Comics>>
        get() = _comics

    init {
        getMarvelComics()
    }

    @SuppressLint("CheckResult")
    private fun getMarvelComics() {
        repository.getAllComics()
            .subscribe(::onSuccess,::onError)
    }
    private fun onSuccess(state: State<MarvelResponse<Comics>>) {
        when (state) {
            is State.Error -> TODO("wait ui")
            State.Loading -> TODO("wait ui")
            is State.Success -> {
                _comics.postValue(state.toData()?.data?.results)
                Log.v("MarvelAPI", "${state.toData()?.data?.results}")
            }
        }
    }

    private fun onError(e: Throwable) {
        Log.e("MarvelAPI", "getMarvelStories() - Error: ${e.message}")
    }

    override fun onClickComic(comics: Comics) {
        TODO("Not yet implemented")
    }


}
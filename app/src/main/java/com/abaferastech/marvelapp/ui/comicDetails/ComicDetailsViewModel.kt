package com.abaferastech.marvelapp.ui.comicDetails

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.abaferastech.marvelapp.data.model.Characters
import com.abaferastech.marvelapp.data.model.Comics
import com.abaferastech.marvelapp.data.model.response.MarvelResponse
import com.abaferastech.marvelapp.data.model.state.State
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.ui.base.BaseViewModel
import io.reactivex.rxjava3.kotlin.addTo

class ComicDetailsViewModel: BaseViewModel() {

    private val repository = MarvelRepository()


    private val _comics = MutableLiveData<Comics>()
    val comics: LiveData<Comics> get() = _comics


    fun getSingleComic(comicId: Int) {
        repository.getSingleComic(comicId)
            .subscribe(::onSuccess, ::onError)
            .addTo(compositeDisposable)
    }
    private fun onSuccess(state: State<MarvelResponse<Comics>>) {
        when (state) {
            is State.Error -> Log.i("cha ra ter er or", "onError:${state} ")
            State.Loading ->" TODO()"
            is State.Success -> {
                state.toData()?.data?.results.toString()
                _comics.postValue(state.toData()?.data?.results?.first())
            }
        }
    }

    private fun onError(e: Throwable) {
        Log.e("MarvelAPI", "getMarvelCharacters() - Error: ${e.message}")
    }


}
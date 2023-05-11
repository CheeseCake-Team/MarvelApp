package com.abaferastech.marvelapp.ui.comic.comicDetails

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.abaferastech.marvelapp.data.model.result.Comics
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.ui.base.BaseViewModel
import com.abaferastech.marvelapp.ui.model.UIState
import io.reactivex.rxjava3.kotlin.addTo


class ComicDetailsViewModel : BaseViewModel() {
    private val repository = MarvelRepository()
    private val _comics = MutableLiveData<Comics>()
    val comics: LiveData<Comics> = _comics


    fun getSingleComic(comicId: Int) {
        repository.getSingleComic(comicId)
            .subscribe(::onSuccess, ::onError)
            .addTo(compositeDisposable)
    }

    private fun onSuccess(state: UIState<Comics>) {
        when (state) {
            is UIState.Error -> Log.i("COMIC_ERROR", "onError:${state} ")
            UIState.Loading -> {} //TODO
            is UIState.Success -> {
                //state.toData()?.data?.results.toString()
                //Log.i("COMIC_SUCCESS", "${ state.toData()?.data?.results.toString()} ")
                //_comics.postValue(state.toData()?.data?.results?.first())
                _comics.postValue(state.data!!)
            }
        }
    }

    private fun onError(e: Throwable) {
        Log.e("COMIC_ON_ERROR", "getSingleComic(): ->>> ${e.message}")
    }
}


package com.abaferastech.marvelapp.ui.comic.comics

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.abaferastech.marvelapp.data.model.result.Comics
import com.abaferastech.marvelapp.data.model.response.MarvelBaseResponse
import com.abaferastech.marvelapp.data.model.uimodel.UIState
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.ui.base.BaseViewModel
import io.reactivex.rxjava3.kotlin.addTo

class ComicsViewModel: BaseViewModel() {
        private val repository = MarvelRepository()

        private val _comics = MutableLiveData<List<Comics>>()
        val comics: LiveData<List<Comics>> get() = _comics


        fun getMarvelComics() {
            repository.getAllComics()
                .subscribe(::onSuccess, ::onError)
                .addTo(compositeDisposable)
        }
        fun getCharacterComics(characterId:Int) {
            repository.getCharacterComics(characterId)
                .subscribe(::onSuccess, ::onError)
                .addTo(compositeDisposable)
        }

        private fun onSuccess(state: UIState<MarvelBaseResponse<Comics>>) {
            when (state) {
                is UIState.Error -> TODO()
                UIState.Loading -> TODO()
                is UIState.Success -> {
                    Log.i("Mujtaba",state.toData()?.data?.results.toString())
                    _comics.postValue(state.toData()?.data?.results)
                }
            }
        }

        private fun onError(e: Throwable) {
            Log.e("MarvelAPI", "getMarvelComics() - Error: ${e.message}")
        }
}
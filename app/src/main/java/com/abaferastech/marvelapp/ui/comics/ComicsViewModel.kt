package com.abaferastech.marvelapp.ui.comics

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.abaferastech.marvelapp.data.model.Comics
import com.abaferastech.marvelapp.data.model.response.MarvelResponse
import com.abaferastech.marvelapp.data.model.state.State
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.ui.base.BaseViewModel
import io.reactivex.rxjava3.kotlin.addTo

class ComicsViewModel: BaseViewModel() {
        private val repository = MarvelRepository()

        private val _comics = MutableLiveData<List<Comics>>()
        val comics: LiveData<List<Comics>> get() = _comics

        init {
            getMarvelComics()
        }

        private fun getMarvelComics() {
            repository.getAllComics()
                .subscribe(::onSuccess, ::onError)
                .addTo(compositeDisposable)
        }

        private fun onSuccess(state: State<MarvelResponse<Comics>>) {
            when (state) {
                is State.Error -> TODO()
                State.Loading -> TODO()
                is State.Success -> {
                    Log.i("Mujtaba",state.toData()?.data?.results.toString())
                    _comics.postValue(state.toData()?.data?.results)
                }
            }
        }

        private fun onError(e: Throwable) {
            Log.e("MarvelAPI", "getMarvelComics() - Error: ${e.message}")
        }
}
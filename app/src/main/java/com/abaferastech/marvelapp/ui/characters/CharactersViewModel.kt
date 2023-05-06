package com.abaferastech.marvelapp.ui.characters

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.abaferastech.marvelapp.data.model.Characters
import com.abaferastech.marvelapp.data.model.Events
import com.abaferastech.marvelapp.data.model.response.MarvelResponse
import com.abaferastech.marvelapp.data.model.state.State
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.ui.base.BaseViewModel
import io.reactivex.rxjava3.kotlin.addTo

class CharactersViewModel : BaseViewModel() {
    init {
        getAllCharacters()
    }

    val repository = MarvelRepository()
    val _characters = MutableLiveData<List<Characters>>()


    val characters: LiveData<List<Characters>> get() = _characters

    fun getAllCharacters() {
        repository.getAllCharacters().subscribe(::onSuccess, ::onError).addTo(compositeDisposable)
    }

    private fun onSuccess(state: State<MarvelResponse<Characters>>) {
        when (state) {
            is State.Error -> TODO()
            State.Loading -> TODO()
            is State.Success -> {
                _characters.postValue(state.toData()?.data?.results)
            }
        }
    }

    private fun onError(e: Throwable) {
        Log.e("MarvelAPI", "getMarvelEvents() - Error: ${e.message}")
    }

}
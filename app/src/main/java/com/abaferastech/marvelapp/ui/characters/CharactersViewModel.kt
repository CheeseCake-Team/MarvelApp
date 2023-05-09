package com.abaferastech.marvelapp.ui.characters

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.abaferastech.marvelapp.data.model.result.Characters
import com.abaferastech.marvelapp.data.model.response.MarvelBaseResponse
import com.abaferastech.marvelapp.data.model.uimodel.UIState
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.ui.base.BaseViewModel
import io.reactivex.rxjava3.kotlin.addTo

class CharactersViewModel : BaseViewModel() {
    private val repository = MarvelRepository()

    init {
        getAllCharacters()
    }

    private val _characters = MutableLiveData<List<Characters>>()


    val characters: LiveData<List<Characters>> get() = _characters

    private fun getAllCharacters() {
        repository.getAllCharacters()
            .subscribe(::onSuccess, ::onError)
            .addTo(compositeDisposable)
    }

    private fun onSuccess(state: UIState<MarvelBaseResponse<Characters>>) {
        when (state) {
            is UIState.Error -> TODO()
            UIState.Loading -> TODO()
            is UIState.Success -> {
                _characters.postValue(state.toData()?.data?.results)
            }
        }
    }

    private fun onError(e: Throwable) {
        Log.e("MarvelAPI", "getMarvelEvents() - Error: ${e.message}")
    }

}
package com.abaferastech.marvelapp.ui.characterDetails

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.abaferastech.marvelapp.data.model.result.Characters
import com.abaferastech.marvelapp.data.model.response.MarvelBaseResponse
import com.abaferastech.marvelapp.data.model.uimodel.UIState
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.ui.base.BaseViewModel
import io.reactivex.rxjava3.kotlin.addTo

class CharacterViewModel : BaseViewModel() {
    private val repository = MarvelRepository()
    private val _characters = MutableLiveData<Characters>()
    val characters: LiveData<Characters> = _characters


    fun getSingleCharacter(characterId: Int) {
        repository.getSingleCharacter(characterId)
            .subscribe(::onSuccess, ::onError)
            .addTo(compositeDisposable)
    }

    private fun onSuccess(state: UIState<MarvelBaseResponse<Characters>>) {
        when (state) {
            is UIState.Error -> Log.i("cha ra ter er or", "onError:${state} ")
            UIState.Loading ->" TODO()"
            is UIState.Success -> {
                state.toData()?.data?.results.toString()
                Log.i("wEVevWVWVFWEewfFEWWfFFea", "${ state.toData()?.data?.results.toString()} ")
                _characters.postValue(state.toData()?.data?.results?.first())
            }
        }
    }

    private fun onError(e: Throwable) {
        Log.e("MarvelAPI", "getMarvelCharacters() - Error: ${e.message}")
    }
}
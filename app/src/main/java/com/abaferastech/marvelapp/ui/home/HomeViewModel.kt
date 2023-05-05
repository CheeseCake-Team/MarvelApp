package com.abaferastech.marvelapp.ui.home
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.abaferastech.marvelapp.data.model.Characters
import com.abaferastech.marvelapp.data.model.Comics
import com.abaferastech.marvelapp.data.model.Series
import com.abaferastech.marvelapp.data.model.response.MarvelResponse
import com.abaferastech.marvelapp.data.model.state.State
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.ui.base.BaseViewModel
import io.reactivex.rxjava3.kotlin.addTo
class HomeViewModel : BaseViewModel() {
    private val repository = MarvelRepository()

    private val _characters = MutableLiveData<List<Characters>>()
    val characters: LiveData<List<Characters>> get() = _characters

    private val _comics = MutableLiveData<List<Comics>>()
    val comics: LiveData<List<Comics>> get() = _comics

    private val _series = MutableLiveData<List<Series>>()
    val series: LiveData<List<Series>> get() = _series

    init {
        getMarvelCharacters()
        getMarvelComics()
        getMarvelSeries()
    }
    private fun getMarvelSeries() {
        repository.getAllSeries()
            .subscribe(::onSuccess, ::onError)
            .addTo(compositeDisposable)
    }

    private fun getMarvelComics() {
        repository.getAllComics()
            .subscribe(::onSuccess, ::onError)
            .addTo(compositeDisposable)
    }

    private fun getMarvelCharacters() {
        repository.getAllCharacters()
            .subscribe(::onSuccess, ::onError)
            .addTo(compositeDisposable)
    }

    private fun <T> onSuccess(state: State<MarvelResponse<T>>) {
        when (state) {
            is State.Error -> TODO()
            State.Loading -> TODO()
            is State.Success -> {
                when (state.toData()?.data?.results?.firstOrNull()) {
                    is Characters -> _characters.postValue(state.toData()?.data?.results as List<Characters>?)
                    is Comics -> _comics.postValue(state.toData()?.data?.results as List<Comics>?)
                    is Series -> _series.postValue(state.toData()?.data?.results as List<Series>?)
                }
            }
        }
    }

    private fun onError(e: Throwable) {
        Log.e("MarvelAPI", "getMarvelStories() - Error: ${e.message}")
    }
}
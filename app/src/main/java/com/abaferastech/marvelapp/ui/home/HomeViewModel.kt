package com.abaferastech.marvelapp.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.abaferastech.marvelapp.data.model.Characters
import com.abaferastech.marvelapp.data.model.Comics
import com.abaferastech.marvelapp.data.model.Series
import com.abaferastech.marvelapp.data.model.Tag
import com.abaferastech.marvelapp.data.model.response.MarvelResponse
import com.abaferastech.marvelapp.data.model.state.State
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.ui.base.BaseViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.addTo

class HomeViewModel : BaseViewModel() {
    private val repository = MarvelRepository()

    private val _homeData = MutableLiveData<List<DataItem>?>()
    val homeData: MutableLiveData<List<DataItem>?> get() = _homeData

    init {
        Observable.zip(
            repository.getAllCharacters().toObservable(),
            repository.getAllComics().toObservable(),
            repository.getAllSeries().toObservable()
        ) { characters, comics, series ->
            Triple(characters, comics, series)
        }
            .subscribe(::onSuccessZip, ::onError)
            .addTo(compositeDisposable)
    }

    private fun onSuccessZip(
        results:Triple<State<MarvelResponse<Characters>>,
                State<MarvelResponse<Comics>>,
                State<MarvelResponse<Series>>>
    ) {
        when {
            results.first is State.Success && results.second is State.Success && results.third is State.Success -> {
                val characters = results.first.toData()?.data!!.results
                val comics = results.second.toData()?.data!!.results
                val series = results.third.toData()?.data!!.results
                val data = listOf(
                    DataItem.HeaderItem(characters[2], 0),
                    DataItem.CharacterTagItem(Tag<Characters>("CHARACTERS", characters), 1),
                    DataItem.ComicsTagItem(Tag<Comics>("COMICS", comics), 2),
                    DataItem.SeriesTagItem(Tag<Series>("SERIES", series), 3)
                )
                _homeData.postValue(data)
            }
            else -> {
                // handle error cases
            }
        }
    }


    private fun onError(e: Throwable) {
        Log.e("MarvelAPI", "getMarvelStories() - Error: ${e.message}")
    }
}
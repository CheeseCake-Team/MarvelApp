package com.abaferastech.marvelapp.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.abaferastech.marvelapp.data.model.*
import com.abaferastech.marvelapp.data.model.response.MarvelResponse
import com.abaferastech.marvelapp.data.model.state.State
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.ui.base.BaseViewModel
import com.abaferastech.marvelapp.ui.characters.CharactersInteractionListener
import com.abaferastech.marvelapp.ui.seriesViewAll.SeriesViewAllInteractionListener
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.addTo

class HomeViewModel : BaseViewModel(),ComicsInteractionListener, CharactersInteractionListener,SeriesInteractionListener {
    private val repository = MarvelRepository()

    private val _homeData = MutableLiveData<List<DataItem>?>()
    val homeData: MutableLiveData<List<DataItem>?> get() = _homeData

    init {
        repository.getHomeData()
            .subscribe(::onSuccessZip, ::onError)
            .addTo(compositeDisposable)
    }

    private fun onSuccessZip(
        results:Triple<State<MarvelResponse<Characters>>,
                State<MarvelResponse<Comics>>,
                State<MarvelResponse<Series>>>,



    ) {
        when {
            results.first is State.Success && results.second is State.Success && results.third is State.Success -> {
                val characters = results.first.toData()?.data!!.results
                    .filter { it.thumbnail?.path != "http://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available" }
                val comics = results.second.toData()?.data!!.results
                    .filter { it.thumbnail?.path != "http://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available" }
                val series = results.third.toData()?.data!!.results
                    .filter { it.thumbnail?.path != "http://i.annihil.us/u/prod/marvel/i/mg/b/40/image_not_available" }
                val data = listOf(
                    DataItem.HeaderItem(characters.shuffled().take(3), 0),
                    DataItem.CharacterTagItem(Tag<Characters>("CHARACTERS", characters.shuffled()), 1,this),
                    DataItem.ComicsTagItem(Tag<Comics>("COMICS", comics.shuffled()), 2,this),
                    DataItem.SeriesTagItem(Tag<Series>("SERIES", series.shuffled()), 3,this)
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

    override fun onCharacterClick(character: Characters) {
        TODO("Not yet implemented")
    }

    override fun onClickSeries(Comics: Comics) {
        TODO("Not yet implemented")
    }

    override fun onClickSeries(series: Series) {
        TODO("Not yet implemented")
    }

//    override fun onClickSeries(series: Series) {
//        TODO("Not yet implemented")
//    }
}
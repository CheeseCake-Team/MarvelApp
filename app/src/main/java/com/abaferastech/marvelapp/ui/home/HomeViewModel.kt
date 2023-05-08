package com.abaferastech.marvelapp.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.abaferastech.marvelapp.data.model.*
import com.abaferastech.marvelapp.data.model.response.MarvelResponse
import com.abaferastech.marvelapp.data.model.state.State
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.ui.base.BaseViewModel
import com.abaferastech.marvelapp.ui.characters.CharactersInteractionListener
import com.abaferastech.marvelapp.ui.home.ComicsInteractionListener
import io.reactivex.rxjava3.kotlin.addTo

class HomeViewModel : BaseViewModel(), ComicsInteractionListener, CharactersInteractionListener,SeriesInteractionListener {
    private val repository = MarvelRepository()

    private val _homeData = MutableLiveData<List<DataItem>?>()
    val homeData: LiveData<List<DataItem>?> get() = _homeData

    private val _isCharacterClicked = MutableLiveData<Boolean>()
    val isCharacterClicked: LiveData<Boolean> get() = _isCharacterClicked

    private val _selectedCharacterID = MutableLiveData<Int>()
    val selectedCharacterID: LiveData<Int> get() = _selectedCharacterID



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

            }
        }
    }


    private fun onError(e: Throwable) {
        Log.e("MarvelAPI", "getMarvelStories() - Error: ${e.message}")
    }

    override fun onClickCharacter(character: Characters) {
        _isCharacterClicked.postValue(true)
        _selectedCharacterID.postValue(character.id!!)

    }


    override fun onClickSeries(series: Series) {
        TODO("Not yet implemented")
    }

    override fun onClickComics(comics: Comics) {
        TODO("Not yet implemented")
    }
    fun resetCharacterClickStatus() {
        _isCharacterClicked.value = false
    }


//    override fun onClickSeries(series: Series) {
//        TODO("Not yet implemented")
//    }
}
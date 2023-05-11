package com.abaferastech.marvelapp.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.abaferastech.marvelapp.data.model.*
import com.abaferastech.marvelapp.data.model.response.MarvelBaseResponse
import com.abaferastech.marvelapp.data.model.result.Characters
import com.abaferastech.marvelapp.data.model.result.Comics
import com.abaferastech.marvelapp.data.model.result.Series
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.ui.base.BaseViewModel
import com.abaferastech.marvelapp.ui.characters.CharactersInteractionListener
import com.abaferastech.marvelapp.ui.comic.comics.ComicsInteractionListener
import com.abaferastech.marvelapp.ui.home.adapters.SeriesInteractionListener
import com.abaferastech.marvelapp.ui.model.DataItem
import com.abaferastech.marvelapp.ui.model.Tag
import com.abaferastech.marvelapp.ui.model.UIState
import io.reactivex.rxjava3.kotlin.addTo

class HomeViewModel : BaseViewModel(), ComicsInteractionListener, CharactersInteractionListener,
    SeriesInteractionListener {
    private val repository = MarvelRepository()

    private val _homeData = MutableLiveData<List<DataItem>?>()
    val homeData: LiveData<List<DataItem>?> get() = _homeData




    private val _selectedCharacterItem = MutableLiveData<SentData>()
    val selectedCharacterItem: LiveData<SentData> get() = _selectedCharacterItem


    private val _selectedComicItem = MutableLiveData<SentData>()
    val selectedComicItem: LiveData<SentData> get() = _selectedComicItem






    init {
        repository.getHomeData()
            .subscribe(::onSuccessZip, ::onError)
            .addTo(compositeDisposable)
    }

    private fun onSuccessZip(
        results:Triple<UIState<MarvelBaseResponse<Characters>>,
                UIState<MarvelBaseResponse<Comics>>,
                UIState<MarvelBaseResponse<Series>>>,

    ) {
        when {
            results.first is UIState.Success && results.second is UIState.Success && results.third is UIState.Success -> {
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
        _selectedCharacterItem.postValue(SentData(true,character.id!!))
    }


    override fun onClickSeries(series: Series) {
        //TODO("Not yet implemented")
    }

    override fun onClickComics(comic: Comics) {
        _selectedComicItem.postValue(SentData(true,comic.id!!))
    }


    fun resetCharacterDataSent() {
        _selectedCharacterItem.value?.clicked = false
    }

    fun resetComicDataSent(){
        _selectedComicItem.value?.clicked = false
    }

    data class SentData(var clicked: Boolean,val id: Int)

}


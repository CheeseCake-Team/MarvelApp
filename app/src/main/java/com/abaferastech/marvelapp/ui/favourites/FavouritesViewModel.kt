package com.abaferastech.marvelapp.ui.favourites

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.domain.models.Character
import com.abaferastech.marvelapp.domain.models.Series
import com.abaferastech.marvelapp.ui.base.BaseInteractionListener
import com.abaferastech.marvelapp.ui.base.BaseViewModel
import com.abaferastech.marvelapp.ui.character.characters.CharactersInteractionListener
import com.abaferastech.marvelapp.ui.series.seriesViewAll.SeriesViewAllInteractionListener
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(
    private val repository: MarvelRepository
) : BaseViewModel(), BaseInteractionListener, CharactersInteractionListener ,
    SeriesViewAllInteractionListener {

    private var _favouritesItems = MutableLiveData<FavouriteItems>()
    val favouriteItems: LiveData<FavouriteItems> = _favouritesItems

    var favouritesType = MutableLiveData(FavouritesType.COMICS)

    init {
        getAllCachedComics()
    }

    fun getCachedCharacters() {
        repository.getAllCashedCharacters()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { items ->
                _favouritesItems.postValue(FavouriteItems.FavouriteCharacters(items))

            }
            .addTo(compositeDisposable)

    }

    fun getAllCachedComics() {
        repository.getAllCashedComic()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { items ->
                _favouritesItems.postValue(FavouriteItems.FavouriteComics(items))
            }
            .addTo(compositeDisposable)
    }

fun getAllCachedSeries(){
    repository.getAllCashedSeries().subscribe() { items ->
        _favouritesItems.postValue(FavouriteItems.FavouriteSeries(items))
    }
}

    override fun onClickCharacter(character: Character) {
        TODO("Not yet implemented")
    }

    fun changeFavouriteType(passedType: FavouritesType) {
        favouritesType.postValue(passedType)
    }

    override fun onClickSeries(series: Series) {
        TODO("Not yet implemented")
    }


}
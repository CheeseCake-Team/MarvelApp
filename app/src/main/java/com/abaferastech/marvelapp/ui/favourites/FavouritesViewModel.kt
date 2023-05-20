package com.abaferastech.marvelapp.ui.favourites

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.domain.models.Character
import com.abaferastech.marvelapp.ui.base.BaseInteractionListener
import com.abaferastech.marvelapp.ui.base.BaseViewModel
import com.abaferastech.marvelapp.ui.character.characters.CharactersInteractionListener
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavouritesViewModel @Inject constructor(
    private val repository: MarvelRepository
) : BaseViewModel(), BaseInteractionListener, CharactersInteractionListener {

    private var _favouritesItems = MutableLiveData<FavouriteItems>()
    val favouriteItems: LiveData<FavouriteItems> get() = _favouritesItems

    var favouritesType = MutableLiveData(FavouritesType.COMICS)

    init {
        getAllCachedComics()
    }

    @SuppressLint("CheckResult")
    fun getCachedCharacters() {
        repository.getAllCashedCharacters().subscribe { items ->
            _favouritesItems.postValue(FavouriteItems.FavouriteCharacters(items))

        }
    }

    @SuppressLint("CheckResult")
    fun getAllCachedComics() {
        repository.getAllCashedComic().subscribe { items ->
            _favouritesItems.postValue(FavouriteItems.FavouriteComics(items))
        }
    }

    override fun onClickCharacter(character: Character) {
        TODO("Not yet implemented")
    }

    fun changeFavouriteType(passedType: FavouritesType) {
        favouritesType.postValue(passedType)
    }


}
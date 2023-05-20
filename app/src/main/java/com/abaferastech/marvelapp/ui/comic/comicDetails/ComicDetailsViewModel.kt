package com.abaferastech.marvelapp.ui.comic.comicDetails

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.domain.models.Comic
import com.abaferastech.marvelapp.ui.base.BaseViewModel
import com.abaferastech.marvelapp.ui.model.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ComicDetailsViewModel @Inject constructor(
    val repository: MarvelRepository,
    state: SavedStateHandle
) : BaseViewModel() {

    private val _comics = MutableLiveData<UIState<Comic>>()
    val comics: LiveData<UIState<Comic>> = _comics

    private val _allComics = MutableLiveData<List<Comic>>()
    val allComics: LiveData<List<Comic>> get() = _allComics

    var isFavouriteClicked = MutableLiveData<Boolean>()

    val comicArgs = state.let {
        ComicDetailsFragmentArgs.fromSavedStateHandle(it)
    }


    init {
        getAllCachedComics()
        Log.i(
            "vwWEvwevvwvewwv", ":${
                repository.getAllCashedComic().subscribe { s ->
                    s.toString()
                }
            }")
    }

    fun getSingleComic(passedId: Int? = null) {
        val comicId = passedId ?: comicArgs.comicID
        repository.getSingleComic(comicId)
            .applySchedulersAndPostUIStates(_comics::postValue)
    }

    @SuppressLint("CheckResult")
    fun getAllCachedComics() {
        Log.i("getAllCharacters: ", repository.getAllCashedComic().toString())
        repository.getAllCashedComic().subscribe { comicsList ->
            Log.i("ebrabw", "getAllCharacters: $comicsList")
            _allComics.postValue(comicsList)
            Log.i("ebrabw", "comics: ${_allComics.value}")
        }
    }


    fun insertComic() {
        comics.value?.toData()?.apply { isFavourite = true }?.let { repository.insertComic(it) }
    }

    fun deleteComic() {
        comics.value?.toData()?.let { repository.deleteComic(it) }
    }

}

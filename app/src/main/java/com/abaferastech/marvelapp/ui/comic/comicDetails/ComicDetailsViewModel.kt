package com.abaferastech.marvelapp.ui.comic.comicDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.abaferastech.marvelapp.data.remote.response.ComicDTO
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.ui.base.BaseViewModel
import com.abaferastech.marvelapp.ui.model.UIState


class ComicDetailsViewModel(state: SavedStateHandle) : BaseViewModel() {

    private val repository  by lazy { MarvelRepository() }

    private val _comics = MutableLiveData<UIState<ComicDTO>>()
    val comics: LiveData<UIState<ComicDTO>> = _comics

    val comicArgs = state.let {
        ComicDetailsFragmentArgs.fromSavedStateHandle(it)
    }

    fun getSingleComic(passedId: Int? = null) {
        val comicId = passedId ?: comicArgs.comicID
        repository.getSingleComic(comicId)
            .applySchedulersAndPostUIStates(_comics::postValue)
    }
}

package com.abaferastech.marvelapp.ui.comic.comicDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.abaferastech.marvelapp.data.domain.models.Comic
import com.abaferastech.marvelapp.data.mapper.dtotodomain.ComicMapper
import com.abaferastech.marvelapp.data.remote.response.ComicDTO
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.ui.base.BaseViewModel
import com.abaferastech.marvelapp.ui.model.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ComicDetailsViewModel @Inject constructor(private val repository: MarvelRepository) :
    BaseViewModel() {


    private val _comics = MutableLiveData<Comic>()
    val comics: LiveData<Comic> = _comics

    var comicId = MutableLiveData<Int>()
    private fun getSingleComicById(passedId: Int) {
        repository.getSingleComic(passedId).applySchedulersAndPostUIStates {
            _comics.postValue(ComicMapper().map(it.toData()!!))
        }
    }

}

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
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

@HiltViewModel
class ComicDetailsViewModel
    @Inject constructor(private val repository: MarvelRepository,
                        savedStateHandle: SavedStateHandle) :
    BaseViewModel(savedStateHandle) {

    private val _comic = MutableLiveData<Comic>()
    val comic: LiveData<Comic> = _comic

    init {
        getSingleComicById()
    }

    fun saveComicId(comicId: Int) {
        setSavedStateValue("comicId", comicId)
    }

    private fun getPassedId() = getSavedStateValue<Int>("comicId")

    private fun getSingleComicById() {
        fetchItem {
            repository.getSingleComic(getPassedId()!!)
        }
    }

    private fun fetchItem(getItem: () -> Single<UIState<ComicDTO>>) {
        getItem()
            .applySchedulersAndPostUIStates {
                _comic.postValue(ComicMapper().map(it.toData()!!))
            }
    }

}

package com.abaferastech.marvelapp.ui.comic.comicDetails

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.abaferastech.marvelapp.data.remote.response.ComicDTO
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.domain.models.Comic
import com.abaferastech.marvelapp.ui.base.BaseViewModel
import com.abaferastech.marvelapp.ui.model.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class ComicDetailsViewModel @Inject constructor(
    val repository: MarvelRepository,
    state: SavedStateHandle
) : BaseViewModel() {

    private val _comics = MutableLiveData<UIState<Comic>>()
    val comics: LiveData<UIState<Comic>> = _comics

    var isFavouriteClicked = MutableLiveData<Boolean>()

    private val _isComicFavourite = MutableLiveData(false)
    val isComicFavourite: LiveData<Boolean> get() = _isComicFavourite

    val comicArgs = state.let {
        ComicDetailsFragmentArgs.fromSavedStateHandle(it)
    }

    fun getSingleComic(passedId: Int? = null) {
        val comicId = passedId ?: comicArgs.comicID
        Completable.fromAction {
            repository.getSingleComic(comicId).doOnSuccess {
                when (it) {
                    is UIState.Success -> {
                        _isComicFavourite.postValue(it.toData()?.isFavourite)
                    }

                    else -> {
                        _isComicFavourite.postValue(false)
                    }
                }
            }
                .applySchedulersAndPostUIStates(_comics::postValue)
        }
            .subscribeOn(Schedulers.io())
            .subscribe()
            .addTo(compositeDisposable)
    }

    fun insertComic() {
        Completable.fromAction {
            comics.value?.toData()?.apply { isFavourite = true }?.let { repository.insertComic(it) }
        }
            .subscribeOn(Schedulers.io())
            .subscribe()
            .addTo(compositeDisposable)
    }

    fun deleteComic() {
        Completable.fromAction {
            comics.value?.toData()?.let { repository.deleteComic(it) }
        }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe()
            .addTo(compositeDisposable)
    }

    fun refresh() {
        val comicId = comicArgs.comicID
        getSingleComic(comicId)
    }
}

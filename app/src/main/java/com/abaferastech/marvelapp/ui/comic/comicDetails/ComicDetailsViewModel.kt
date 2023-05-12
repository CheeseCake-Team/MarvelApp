package com.abaferastech.marvelapp.ui.comic.comicDetails

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.abaferastech.marvelapp.data.model.result.Characters
import com.abaferastech.marvelapp.data.model.result.Comics
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.ui.base.BaseViewModel
import com.abaferastech.marvelapp.ui.model.UIState
import io.reactivex.rxjava3.kotlin.addTo


class ComicDetailsViewModel : BaseViewModel() {

    private val repository  by lazy { MarvelRepository() }

    private val _comics = MutableLiveData<UIState<Comics>>()
    val comics: LiveData<UIState<Comics>> = _comics


    fun getSingleComic(comicId: Int) {
        repository.getSingleComic(comicId)
            .applySchedulersAndPostUIStates(_comics::postValue)
    }
}

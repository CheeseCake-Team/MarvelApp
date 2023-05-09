package com.abaferastech.marvelapp.ui.comic.comics

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.abaferastech.marvelapp.data.model.result.Comics
import com.abaferastech.marvelapp.ui.model.UIState
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.ui.base.BaseViewModel

class ComicsViewModel : BaseViewModel() {
    private val repository = MarvelRepository()

    private val _comics = MutableLiveData<UIState<List<Comics>>>()
    val comics: LiveData<UIState<List<Comics>>> get() = _comics

    private val _characterComics = MutableLiveData<UIState<List<Comics>>>()
    val characterComics: LiveData<UIState<List<Comics>>> get() = _characterComics


    fun getMarvelComics() {
        repository.getAllComics().applySchedulersAndSubscribe(_comics::postValue)
    }

    fun getCharacterComics(characterId: Int) {
        repository.getCharacterComics(characterId)
            .applySchedulersAndSubscribe(_characterComics::postValue)
    }


}
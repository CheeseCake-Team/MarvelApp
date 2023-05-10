package com.abaferastech.marvelapp.ui.comic.comics

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.abaferastech.marvelapp.data.model.result.Comics
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.ui.base.BaseViewModel
import com.abaferastech.marvelapp.ui.model.UIState

class ComicsViewModel : BaseViewModel() {
    private val repository = MarvelRepository()

    private val _comics = MutableLiveData<UIState<List<Comics>>>()
    val comics: LiveData<UIState<List<Comics>>> get() = _comics

    fun getMarvelComics() {
        repository.getAllComics().applySchedulersAndPostUIStates(_comics::postValue)
    }

    fun getCharacterComics(characterId: Int) {
        repository.getCharacterComics(characterId)
            .applySchedulersAndPostUIStates(_comics::postValue)
    }

    fun getSeriesComics(seriesId: Int) {
        repository.getSeriesComics(seriesId)
            .applySchedulersAndPostUIStates(_comics::postValue)
    }


    fun getCreatorComics(creatorId: Int) {
        repository.getCreatorComics(creatorId)
            .applySchedulersAndPostUIStates(_comics::postValue)
    }


    fun getEventComics(eventId: Int) {
        repository.getEventComics(eventId)
            .applySchedulersAndPostUIStates(_comics::postValue)
    }


}
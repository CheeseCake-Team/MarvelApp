package com.abaferastech.marvelapp.ui.creator.creators

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.abaferastech.marvelapp.data.model.result.Creators
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.ui.base.BaseViewModel
import com.abaferastech.marvelapp.ui.model.Event
import com.abaferastech.marvelapp.ui.model.UIState

class CreatorsViewModel : BaseViewModel(), CreatorsInteractionListener {
    private val repository = MarvelRepository()

    private val _creators = MutableLiveData<UIState<List<Creators>>>()
    val creators: LiveData<UIState<List<Creators>>> get() = _creators

    val navigationEvents = MutableLiveData<Event<CreatorEvent>>()


    init {
        getMarvelCreators()
    }

    fun getMarvelCreators() {
        repository.getAllCreators()
            .applySchedulersAndPostUIStates(_creators::postValue)
    }

    fun getSeriesCreators(seriesId: Int) {
        repository.getSeriesCreators(seriesId)
            .applySchedulersAndPostUIStates(_creators::postValue)
    }

    fun getComicCreators(comicId: Int) {
        repository.getComicCreators(comicId)
            .applySchedulersAndPostUIStates(_creators::postValue)
    }

    override fun onClickCreators(creators: Creators) {
        navigationEvents.postValue(Event(CreatorEvent.ClickCreatorEvent(creators.id!!)))
    }

}
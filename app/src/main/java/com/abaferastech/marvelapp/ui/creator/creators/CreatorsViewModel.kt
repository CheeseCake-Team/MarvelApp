package com.abaferastech.marvelapp.ui.creator.creators

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.domain.models.Creator
import com.abaferastech.marvelapp.ui.base.BaseViewModel
import com.abaferastech.marvelapp.ui.model.EventModel
import com.abaferastech.marvelapp.ui.model.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel

class CreatorsViewModel @Inject constructor(val repository:MarvelRepository) : BaseViewModel(), CreatorsInteractionListener {

    private val _creators = MutableLiveData<UIState<List<Creator>>>()
    val creators: LiveData<UIState<List<Creator>>> get() = _creators

    val navigationEvents = MutableLiveData<EventModel<CreatorEvent>>()


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

    override fun onClickCreators(creators: Creator) {
        navigationEvents.postValue(EventModel(CreatorEvent.ClickCreatorEvent(creators.id!!)))
    }

}
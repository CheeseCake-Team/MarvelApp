package com.abaferastech.marvelapp.ui.comic.comics

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.abaferastech.marvelapp.data.remote.response.ComicDTO
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.domain.models.Comic
import com.abaferastech.marvelapp.ui.base.BaseViewModel
import com.abaferastech.marvelapp.ui.model.EventModel
import com.abaferastech.marvelapp.ui.model.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel

class ComicsViewModel @Inject constructor( val repository:MarvelRepository) : BaseViewModel(), ComicsInteractionListener {


    private val _comics = MutableLiveData<UIState<List<Comic>>>()
    val comics: LiveData<UIState<List<Comic>>> get() = _comics

    val navigationEvents = MutableLiveData<EventModel<ComicEvents>>()


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

    override fun onClickComic(comic: Comic) {
        navigationEvents.postValue(EventModel(ComicEvents.ClickComicEvent(comic.id)))
    }


}
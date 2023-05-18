package com.abaferastech.marvelapp.ui.comic.comics

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.abaferastech.marvelapp.data.domain.models.Comic
import com.abaferastech.marvelapp.data.mapper.dtotodomain.ComicMapper
import com.abaferastech.marvelapp.data.remote.response.ComicDTO
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.ui.base.BaseViewModel
import com.abaferastech.marvelapp.ui.model.Event
import com.abaferastech.marvelapp.ui.model.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

@HiltViewModel
class ComicsViewModel @Inject constructor(
    private val repository: MarvelRepository,
    savedStateHandle: SavedStateHandle
) :
    BaseViewModel(savedStateHandle), ComicsInteractionListener {


    private val _comics = MutableLiveData<List<Comic>>()
    val comics: LiveData<List<Comic>> get() = _comics

    val navigationEvents = MutableLiveData<Event<ComicEvents>>()


    override val key: String
        get() = "comicsId"


    private fun convertDtoToListDomain(list: List<ComicDTO>): MutableList<Comic> {
        val result = mutableListOf<Comic>()
        list.forEach {
            result.add(ComicMapper().map(it))
        }
        return result
    }

    fun getMarvelComics() {
        fetchItemsList {
            repository.getAllComics()
        }
    }

    fun getCharacterComics() {
        fetchItemsList {
            repository.getCharacterComics(getPassedId()!!)
        }
    }

    fun getSeriesComics() {
        fetchItemsList {
            repository.getSeriesComics(getPassedId()!!)
        }
    }


    fun getCreatorComics() {
        fetchItemsList {
            repository.getCreatorComics(getPassedId()!!)
        }
    }


    fun getEventComics() {
        fetchItemsList {
            repository.getEventComics(getPassedId()!!)
        }
    }

    override fun onClickComic(comic: Comic) {
        navigationEvents.postValue(Event(ComicEvents.ClickComicEvent(comic.id)))
    }

    private fun fetchItemsList(getItemsList: () -> Single<UIState<List<ComicDTO>>>) {
        getItemsList()
            .applySchedulersAndPostUIStates { dtoList ->
                _comics.postValue(convertDtoToListDomain(dtoList.toData()!!))
            }
    }


}
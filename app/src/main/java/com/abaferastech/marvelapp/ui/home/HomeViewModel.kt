package com.abaferastech.marvelapp.ui.home

import android.os.Parcelable
import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.abaferastech.marvelapp.data.remote.response.CharacterDTO
import com.abaferastech.marvelapp.data.remote.response.ComicDTO
import com.abaferastech.marvelapp.data.remote.response.SeriesDTO
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.ui.base.BaseViewModel
import com.abaferastech.marvelapp.ui.character.characters.CharactersInteractionListener
import com.abaferastech.marvelapp.ui.home.adapters.ComicsInteractionListener
import com.abaferastech.marvelapp.ui.home.adapters.NavigationInteractionListener
import com.abaferastech.marvelapp.ui.home.adapters.SeriesInteractionListener
import com.abaferastech.marvelapp.ui.model.DataItem
import com.abaferastech.marvelapp.ui.model.Event
import com.abaferastech.marvelapp.ui.model.Tag
import com.abaferastech.marvelapp.ui.model.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(val repository: MarvelRepository) : BaseViewModel(),
    ComicsInteractionListener, CharactersInteractionListener, SeriesInteractionListener,
    NavigationInteractionListener {

    private lateinit var state: Parcelable


    private val _homeData = MediatorLiveData<UIState<List<DataItem>>>()
    val homeData = _homeData

    private val _characters = MutableLiveData<UIState<List<CharacterDTO>>>()
    private val _comics = MutableLiveData<UIState<List<ComicDTO>>>()
    private val _series = MutableLiveData<UIState<List<SeriesDTO>>>()
    private val data = mutableListOf<DataItem>()


    val navigationEvents = MutableLiveData<Event<HomeEvent>>()


    init {
        _homeData.postValue(UIState.Loading)
        _homeData.addSource(_characters) {
            updateCharacterDataItem()
        }
        _homeData.addSource(_comics) {
            updateComicsDataItem()
        }
        _homeData.addSource(_series) {
            updateSeriesDataItem()
        }

        repository.getAllCharacters().applySchedulersAndPostUIStates(_characters::postValue)
        repository.getAllComics().applySchedulersAndPostUIStates(_comics::postValue)
        repository.getAllSeries().applySchedulersAndPostUIStates(_series::postValue)
    }

    private fun updateCharacterDataItem() {
        if (_characters.value is UIState.Success) {
            val characters = _characters.value?.toData()
            data.add(DataItem.HeaderItem(characters?.shuffled()?.take(4)!!))
            data.add(
                DataItem.CharacterTagItem(
                    Tag(
                        id = 1, title = "CHARACTERS", ResourcesData = characters.shuffled()
                    ), this
                )
            )
            _homeData.postValue(UIState.Success(data))
        }

    }


    private fun updateComicsDataItem() {
        if (_characters.value is UIState.Success) {
            val comics = _comics.value?.toData() ?: emptyList()
            data.add(
                DataItem.ComicsTagItem(
                    Tag(
                        id = 2, title = "COMICS", ResourcesData = comics.shuffled()
                    ), this
                )
            )
            Log.d("TaDa", "updateComicsDataItem: $data")

            _homeData.postValue(UIState.Success(data))
        }
    }

    private fun updateSeriesDataItem() {
        if (_characters.value is UIState.Success) {
            val series = _series.value?.toData()
            data.add(
                DataItem.SeriesTagItem(
                    Tag(id = 3, title = "SERIES", ResourcesData = series?.shuffled()!!),
                    this
                )
            )
            Log.d("TaDa", "updateSeriesDataItem: $data")
            _homeData.postValue(UIState.Success(data))
        }
        Log.d("TaDa", "updateSeriesDataItem: $data")
    }

    override fun onCleared() {
        super.onCleared()
        _homeData.removeSource(_characters)
        _homeData.removeSource(_comics)
        _homeData.removeSource(_series)
    }


    override fun onClickCharacter(character: CharacterDTO) {
        navigationEvents.postValue(Event(HomeEvent.ClickCharacterEvent(character.id!!)))
    }

    override fun onClickSeries(series: SeriesDTO) {
        navigationEvents.postValue(Event(HomeEvent.ClickSeriesEvent(series.id)))
    }

    override fun onClickComics(comics: ComicDTO) {
        navigationEvents.postValue(Event(HomeEvent.ClickComicEvent(comics.id!!)))
    }


    override fun onNavigate(id: Int) {
        when (id) {
            1 -> navigationEvents.postValue(Event(HomeEvent.ClickAllCharacterEvent))
            2 -> navigationEvents.postValue(Event(HomeEvent.ClickAllComicEvent))
            3 -> navigationEvents.postValue(Event(HomeEvent.ClickAllSeriesEvent))
        }
    }

    fun saveRecyclerViewState(parcelable: Parcelable) {
        state = parcelable
    }

    fun restoreRecyclerViewState(): Parcelable = state
    fun stateInitialized(): Boolean = ::state.isInitialized


}
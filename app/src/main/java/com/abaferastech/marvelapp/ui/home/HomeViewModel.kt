package com.abaferastech.marvelapp.ui.home

import android.os.Parcelable
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.abaferastech.marvelapp.data.model.result.Characters
import com.abaferastech.marvelapp.data.model.result.Comics
import com.abaferastech.marvelapp.data.model.result.Series
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

class HomeViewModel : BaseViewModel(), ComicsInteractionListener, CharactersInteractionListener,
    SeriesInteractionListener, NavigationInteractionListener {
    private val repository = MarvelRepository()

    private val _homeData = MediatorLiveData<UIState<List<DataItem>>>()
    val homeData: MediatorLiveData<UIState<List<DataItem>>> get() = _homeData

    private val _characters = MutableLiveData<UIState<List<Characters>>>()
    private val _comics = MutableLiveData<UIState<List<Comics>>>()
    private val _series = MutableLiveData<UIState<List<Series>>>()

    val navigationEvents = MutableLiveData<Event<HomeEvent>>()


    val data = mutableListOf<DataItem>()

    private lateinit var state: Parcelable
    fun saveRecyclerViewState(parcelable: Parcelable) {
        state = parcelable
    }

    fun restoreRecyclerViewState(): Parcelable = state
    fun stateInitialized(): Boolean = ::state.isInitialized

    init {
        _homeData.addSource(_characters) {
            updateCharacterDataItem()
            checkAllDataSourcesUpdated()
        }
        _homeData.addSource(_comics) {
            updateComicsDataItem()
            checkAllDataSourcesUpdated()
        }
        _homeData.addSource(_series) {
            updateSeriesDataItem()
            checkAllDataSourcesUpdated()
        }

        _homeData.postValue(UIState.Loading)

        repository.getAllCharacters().applySchedulersAndPostUIStates(_characters::postValue)
        repository.getAllComics().applySchedulersAndPostUIStates(_comics::postValue)
        repository.getAllSeries().applySchedulersAndPostUIStates(_series::postValue)
    }

    private fun updateCharacterDataItem() {
        val characters = _characters.value?.toData()
        data.add(DataItem.HeaderItem(characters?.shuffled()?.take(4)!!))
        data.add(
            DataItem.CharacterTagItem(Tag("CHARACTERS", characters.shuffled()), this)
        )
    }

    private fun checkAllDataSourcesUpdated() {
        if (data.size == 4) {
            _homeData.postValue(UIState.Success(data.sortedBy { it.rank }))
        }
    }

    private fun updateComicsDataItem() {
        val comics = _comics.value?.toData() ?: emptyList()
        data.add(DataItem.ComicsTagItem(Tag("COMICS", comics.shuffled()), this))
    }

    private fun updateSeriesDataItem() {
        val series = _series.value?.toData() ?: emptyList()
        data.add(DataItem.SeriesTagItem(Tag("SERIES", series.shuffled()), this))
    }

    override fun onCleared() {
        super.onCleared()
        _homeData.removeSource(_characters)
        _homeData.removeSource(_comics)
        _homeData.removeSource(_series)
    }


    override fun onClickCharacter(character: Characters) {
        navigationEvents.postValue(Event(HomeEvent.ClickCharacterEvent(character.id!!)))
    }

    override fun onClickSeries(series: Series) {
        navigationEvents.postValue(Event(HomeEvent.ClickSeriesEvent(series.id)))
    }

    override fun onClickComics(comics: Comics) {
        navigationEvents.postValue(Event(HomeEvent.ClickComicEvent(comics.id!!)))
    }

    override fun onNavigate(dataItem: DataItem) {
//        when (dataItem) {
//            is DataItem.CharacterTagItem -> {
//                val action = HomeFragmentDirections.actionHomeFragmentToCharactersFragment()
//                findNavController().navigate(action)
//            }
//
//            is DataItem.ComicsTagItem -> {
//                val action = HomeFragmentDirections.actionHomeFragmentToComicsGridFragment()
//                findNavController().navigate(action)
//            }
//
//            is DataItem.SeriesTagItem -> {
//                val action = HomeFragmentDirections.actionHomeFragmentToSeriesViewAllFragment()
//                findNavController().navigate(action)
//            }
//            else -> {}
//        }

    }

}
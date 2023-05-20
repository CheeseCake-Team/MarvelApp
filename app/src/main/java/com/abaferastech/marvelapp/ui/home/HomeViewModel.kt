package com.abaferastech.marvelapp.ui.home

import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.domain.models.Character
import com.abaferastech.marvelapp.domain.models.Comic
import com.abaferastech.marvelapp.domain.models.Series
import com.abaferastech.marvelapp.ui.base.BaseViewModel
import com.abaferastech.marvelapp.ui.character.characters.CharactersInteractionListener
import com.abaferastech.marvelapp.ui.home.adapters.ComicsInteractionListener
import com.abaferastech.marvelapp.ui.home.adapters.NavigationInteractionListener
import com.abaferastech.marvelapp.ui.home.adapters.SeriesInteractionListener
import com.abaferastech.marvelapp.ui.model.DataItem
import com.abaferastech.marvelapp.ui.model.EventModel
import com.abaferastech.marvelapp.ui.model.Tag
import com.abaferastech.marvelapp.ui.model.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(val repository: MarvelRepository) : BaseViewModel(),
    ComicsInteractionListener, CharactersInteractionListener, SeriesInteractionListener,
    NavigationInteractionListener {

    private lateinit var state: Parcelable


    private val _homeData = MutableLiveData<UIState<List<DataItem>>>()
    val homeData: LiveData<UIState<List<DataItem>>> = _homeData

    private val data = mutableListOf<DataItem>()


    val navigationEvents = MutableLiveData<EventModel<HomeEvent>>()


    init {

        getAllHomeData()

    }

    private fun onError(e: Throwable) {
        _homeData.postValue(UIState.Error(e.message.toString()))
    }

    private fun onCharacterSuccess(charactersState: UIState<List<Character>>) {
        val characters = charactersState.toData()!!
        data.add(DataItem.HeaderItem(characters.shuffled().take(4)))
        data.add(
            DataItem.CharacterTagItem(
                Tag(id = 1, title = "CHARACTERS", ResourcesData = characters.shuffled()),
                this
            )
        )
        _homeData.postValue(UIState.Success(data))
    }


    private fun onComicsSuccess(comicsState: UIState<List<Comic>>) {
        val comics = comicsState.toData()!!
        data.add(
            DataItem.ComicsTagItem(
                Tag(
                    id = 2, title = "COMICS", ResourcesData = comics.shuffled()
                ), this
            )
        )

        _homeData.postValue(UIState.Success(data))
    }

    private fun onSeriesSuccess(seriesState: UIState<List<Series>>) {
        val series = seriesState.toData()
        data.add(
            DataItem.SeriesTagItem(
                Tag(id = 3, title = "SERIES", ResourcesData = series?.shuffled()!!),
                this
            )
        )
        _homeData.postValue(UIState.Success(data))
    }

    override fun onClickCharacter(character: Character) {
        navigationEvents.postValue(EventModel(HomeEvent.ClickCharacterEvent(character.id)))
    }

    override fun onClickSeries(series: Series) {
        navigationEvents.postValue(EventModel(HomeEvent.ClickSeriesEvent(series.id)))
    }

    override fun onClickComics(comics: Comic) {
        navigationEvents.postValue(EventModel(HomeEvent.ClickComicEvent(comics.id)))
    }


    override fun onNavigate(id: Int) {
        when (id) {
            1 -> navigationEvents.postValue(EventModel(HomeEvent.ClickAllCharacterEvent))
            2 -> navigationEvents.postValue(EventModel(HomeEvent.ClickAllComicEvent))
            3 -> navigationEvents.postValue(EventModel(HomeEvent.ClickAllSeriesEvent))
        }
    }

    fun saveRecyclerViewState(parcelable: Parcelable) {
        state = parcelable
    }

    fun restoreRecyclerViewState(): Parcelable = state
    fun stateInitialized(): Boolean = ::state.isInitialized

    fun getAllHomeData(){

        _homeData.postValue(UIState.Loading)

        repository.getAllCharacters()
            .applySchedulersAndPostUIStates(::onCharacterSuccess, ::onError)


        repository.getAllComics()
            .applySchedulersAndPostUIStates(::onComicsSuccess, ::onError)


        repository.getAllSeries()
            .applySchedulersAndPostUIStates(::onSeriesSuccess, ::onError)
    }
}
package com.abaferastech.marvelapp.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.abaferastech.marvelapp.data.model.result.Characters
import com.abaferastech.marvelapp.data.model.result.Comics
import com.abaferastech.marvelapp.data.model.result.Series
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.ui.base.BaseViewModel
import com.abaferastech.marvelapp.ui.characters.CharactersInteractionListener
import com.abaferastech.marvelapp.ui.home.adapters.ComicsInteractionListener
import com.abaferastech.marvelapp.ui.home.adapters.SeriesInteractionListener
import com.abaferastech.marvelapp.ui.model.DataItem
import com.abaferastech.marvelapp.ui.model.Tag
import com.abaferastech.marvelapp.ui.model.UIState
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class HomeViewModel : BaseViewModel(), ComicsInteractionListener, CharactersInteractionListener,
    SeriesInteractionListener {
    private val repository = MarvelRepository()

    private val _homeData = MediatorLiveData<List<DataItem>>()
    val homeData: MediatorLiveData<List<DataItem>> get() = _homeData

    private val _characters = MutableLiveData<UIState<List<Characters>>>()
    private val _comics = MutableLiveData<UIState<List<Comics>>>()
    private val _series = MutableLiveData<UIState<List<Series>>>()


    private val _isCharacterClicked = MutableLiveData<Boolean>()
    val isCharacterClicked: LiveData<Boolean> get() = _isCharacterClicked

    private val _selectedCharacterID = MutableLiveData<Int>()
    val selectedCharacterID: LiveData<Int> get() = _selectedCharacterID



    private val _allDataState = MutableLiveData<UIState<Any>>()
    val allDataState: LiveData<UIState<Any>> get() = _allDataState
    private val disposables = CompositeDisposable()

    init {
        _homeData.addSource(_characters) { updateHomeData() }
        _homeData.addSource(_comics) { updateHomeData() }
        _homeData.addSource(_series) { updateHomeData() }

        _allDataState.value = UIState.Loading

        repository.getAllCharacters().applySchedulersAndPostUIStates { uiState ->
            _characters.postValue(uiState)
            checkLoadingState()
        }
        repository.getAllComics().applySchedulersAndPostUIStates { uiState ->
            _comics.postValue(uiState)
            checkLoadingState()
        }
        repository.getAllSeries().applySchedulersAndPostUIStates { uiState ->
            _series.postValue(uiState)
            checkLoadingState()
        }
    }

    private fun checkLoadingState() {
        if (_characters.value !is UIState.Loading &&
            _comics.value !is UIState.Loading &&
            _series.value !is UIState.Loading
        ) {
            // delay before posting success state to simulate longer loading
            val delayObservable = Observable.timer(3, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    _allDataState.postValue(UIState.Success(Unit))
                }

            // add delayObservable to disposables
            disposables.add(delayObservable)
        }
    }

    private fun updateHomeData() {

        val characters = _characters.value?.toData() ?: emptyList()
        val comics = _comics.value?.toData() ?: emptyList()
        val series = _series.value?.toData() ?: emptyList()

        Log.d("TAG", "characters updateHomeData: $characters")
        Log.d("TAG", "comics updateHomeData: $comics")
        Log.d("TAG", "series updateHomeData: $series")

        val data = listOf(
            DataItem.HeaderItem(characters.shuffled().take(3), 0),
            DataItem.CharacterTagItem(
                Tag("CHARACTERS", characters.shuffled()), 1, this
            ),
            DataItem.ComicsTagItem(Tag("COMICS", comics.shuffled()), 2, this),
            DataItem.SeriesTagItem(Tag("SERIES", series.shuffled()), 3, this)
        )
        _homeData.postValue(data)
    }

    override fun onCleared() {
        super.onCleared()
        _homeData.removeSource(_characters)
        _homeData.removeSource(_comics)
        _homeData.removeSource(_series)
        disposables.clear()  // clear disposables
    }

    override fun onClickCharacter(character: Characters) {
        _isCharacterClicked.postValue(true)
        _selectedCharacterID.postValue(character.id!!)
    }


    override fun onClickSeries(series: Series) {
        TODO("Not yet implemented")
    }

    override fun onClickComics(comics: Comics) {
        TODO("Not yet implemented")
    }

    fun resetCharacterClickStatus() {
        _isCharacterClicked.value = false
    }
}
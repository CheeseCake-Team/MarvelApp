package com.abaferastech.marvelapp.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
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

class HomeViewModel : BaseViewModel(), ComicsInteractionListener, CharactersInteractionListener,
    SeriesInteractionListener {
    private val repository = MarvelRepository()

    private val _characters = MutableLiveData<UIState<List<Characters>>>()
    private val _comics = MutableLiveData<UIState<List<Comics>>>()
    private val _series = MutableLiveData<UIState<List<Series>>>()

    private val _homeData = MediatorLiveData<List<DataItem>>()
        .apply {
            addSource(_characters) { updateHomeData() }
            addSource(_comics) { updateHomeData() }
            addSource(_series) { updateHomeData() }
        }
    val homeData: MediatorLiveData<List<DataItem>> get() = _homeData


    private val _isCharacterClicked = MutableLiveData<Boolean>(false)
    val isCharacterClicked: LiveData<Boolean> get() = _isCharacterClicked

    private val _selectedCharacterID = MutableLiveData<Int>()
    val selectedCharacterID: LiveData<Int> get() = _selectedCharacterID

    private val _navigated = MutableLiveData<Boolean?>()

    init {
        repository.getAllCharacters().applySchedulersAndPostUIStates(_characters::postValue)
        repository.getAllComics().applySchedulersAndPostUIStates(_comics::postValue)
        repository.getAllSeries().applySchedulersAndPostUIStates(_series::postValue)
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
    }

    override fun onClickCharacter(character: Characters) {
        if (character.id != null) {
            _isCharacterClicked.value = true
            _selectedCharacterID.postValue(character.id!!)
        }
    }

    /**
     * Returns the content if it has not been handled, or null if it has already been handled.
     */
//    fun <T> LiveData<T>.getContentIfNotHandled(): T? {
//        val content = this.value
//        if (content != null) {
//            this.value = null
//        }
//        return content
//    }

    fun onNavigationHandled() {
        _navigated.value = null
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
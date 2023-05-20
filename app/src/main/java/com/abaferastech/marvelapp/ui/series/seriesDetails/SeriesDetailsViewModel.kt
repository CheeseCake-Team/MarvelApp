package com.abaferastech.marvelapp.ui.series.seriesDetails

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.abaferastech.marvelapp.data.remote.response.SeriesDTO
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.domain.models.Character
import com.abaferastech.marvelapp.domain.models.Series
import com.abaferastech.marvelapp.ui.base.BaseViewModel
import com.abaferastech.marvelapp.ui.model.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel

class SeriesDetailsViewModel @Inject constructor(
    private val repository: MarvelRepository,
    state: SavedStateHandle
) : BaseViewModel() {

    private val _series = MutableLiveData<UIState<Series>>()
    val series: LiveData<UIState<Series>> get() = _series

    val seriesArgs = state.let {
        SeriesDetailsFragmentArgs.fromSavedStateHandle(it)
    }

    var isFavouriteClicked = MutableLiveData<Boolean>()

    val allSeries = MutableLiveData<List<Series>>()

    val _isSeriesFavourite = MutableLiveData<Boolean>(false)
    val isSeriesFavourite : LiveData<Boolean> get() = _isSeriesFavourite


    fun getSeriesById(passeId: Int? = null) {
        val seriesId = passeId ?: seriesArgs.seriesId
        repository.getSingleSeries(seriesId)
            .applySchedulersAndPostUIStates(_series::postValue)
    }

    private fun checkIfFavourite() {
        val characterId = series.value?.toData()?.id

        _isSeriesFavourite.postValue(allSeries.value?.any { it.id == characterId })
    }

    init {
        getAllSeries()
        checkIfFavourite()
    }

    fun insertSeries() {
        series.value?.toData()?.apply { isFavourite = true }?.let { repository.insertSeries(it) }
    }

    fun deleteSeries() {
        series.value?.toData()?.let { repository.deleteSeries(it) }
    }

    @SuppressLint("CheckResult")
    fun getAllSeries() {
        repository.getAllCashedCSeries().subscribe { characterList ->
            allSeries.postValue(characterList)
        }
    }

    fun onFavouriteClick() {
        // Handle the logic when the button is clicked
        if (isFavouriteClicked.value == true) {
            isFavouriteClicked.postValue(false)
        } else {
            isFavouriteClicked.postValue(true)
        }
    }

}

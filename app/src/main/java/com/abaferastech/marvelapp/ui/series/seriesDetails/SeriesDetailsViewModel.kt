package com.abaferastech.marvelapp.ui.series.seriesDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.abaferastech.marvelapp.data.domain.models.Comic
import com.abaferastech.marvelapp.data.remote.response.SeriesDTO
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.ui.base.BaseViewModel
import com.abaferastech.marvelapp.ui.model.UIState
import javax.inject.Inject

class SeriesDetailsViewModel
@Inject constructor(private val repository: MarvelRepository,
                    savedStateHandle: SavedStateHandle)
    : BaseViewModel(savedStateHandle) {

    private val _comic = MutableLiveData<Comic>()
    val comic: LiveData<Comic> = _comic
    override val key: String
        get() = ""

    val seriesArgs = state.let {
        SeriesDetailsFragmentArgs.fromSavedStateHandle(it)
    }

    fun getSeriesById(passeId: Int? = null){
        val seriesId = passeId ?: seriesArgs.seriesId
        repository.getSingleSeries(seriesId)
            .applySchedulersAndPostUIStates(_series::postValue)
    }

}

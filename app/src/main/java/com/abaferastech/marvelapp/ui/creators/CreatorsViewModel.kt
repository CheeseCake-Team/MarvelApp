package com.abaferastech.marvelapp.ui.creators

import android.widget.BaseAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.abaferastech.marvelapp.data.model.result.Creators
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.ui.base.BaseViewModel
import com.abaferastech.marvelapp.ui.model.UIState

class CreatorsViewModel: BaseViewModel() {
    private val repository = MarvelRepository()

    private val _creators = MutableLiveData<UIState<List<Creators>>>()
    val creators: LiveData<UIState<List<Creators>>> get() =  _creators

    init {
        getMarvelCreators()
    }

    fun getMarvelCreators() {
        repository.getAllCreators()
            .applySchedulersAndPostUIStates (_creators::postValue)
    }

    fun getSeriesCreators(seriesId: Int) {
        repository.getSeriesCreators(seriesId)
            .applySchedulersAndPostUIStates(_creators::postValue)
    }

}
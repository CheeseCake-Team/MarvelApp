package com.abaferastech.marvelapp.ui.creator.creatorsDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.abaferastech.marvelapp.data.model.result.Creators
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.ui.base.BaseViewModel
import com.abaferastech.marvelapp.ui.model.UIState

class CreatorDetailsViewModel(state: SavedStateHandle) : BaseViewModel() {
    private val repository by lazy { MarvelRepository() }

    private val _creator = MutableLiveData<UIState<Creators>>()
    val creator: LiveData<UIState<Creators>> get() = _creator

    val creatorArgs = state.let {
        CreatorDetailsFragmentArgs.fromSavedStateHandle(it)
    }


    fun getMarvelCreator(passedId: Int? = null) {
        val creatorId = passedId ?: creatorArgs.creatorId
        repository.getSingleCreator(creatorId)
            .applySchedulersAndPostUIStates(_creator::postValue)
    }

}









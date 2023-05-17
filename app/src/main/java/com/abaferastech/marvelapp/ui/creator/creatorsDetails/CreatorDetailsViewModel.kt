package com.abaferastech.marvelapp.ui.creator.creatorsDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.abaferastech.marvelapp.data.remote.response.CreatorDTO
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.ui.base.BaseViewModel
import com.abaferastech.marvelapp.ui.model.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class CreatorDetailsViewModel @Inject constructor(private val repository: MarvelRepository) : BaseViewModel() {

    private val _creator = MutableLiveData<UIState<CreatorDTO>>()
    val creator: LiveData<UIState<CreatorDTO>> get() = _creator

    /*val creatorArgs = state.let {
        CreatorDetailsFragmentArgs.fromSavedStateHandle(it)
    }


    fun getMarvelCreator(passedId: Int? = null) {
        val creatorId = passedId ?: creatorArgs.creatorId
        repository.getSingleCreator(creatorId)
            .applySchedulersAndPostUIStates(_creator::postValue)
    }*/

}









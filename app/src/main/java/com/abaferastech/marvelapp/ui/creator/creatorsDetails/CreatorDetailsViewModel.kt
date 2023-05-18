package com.abaferastech.marvelapp.ui.creator.creatorsDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.abaferastech.marvelapp.data.domain.models.Creator
import com.abaferastech.marvelapp.data.mapper.dtotodomain.CreatorMapper
import com.abaferastech.marvelapp.data.remote.response.CreatorDTO
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.ui.base.BaseViewModel
import com.abaferastech.marvelapp.ui.model.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CreatorDetailsViewModel @Inject constructor(
    private val repository: MarvelRepository,
    savedStateHandle: SavedStateHandle
) : BaseViewModel(savedStateHandle) {

    private val _creator = MutableLiveData<Creator>()
    val creator: LiveData<Creator> get() = _creator

    init {
        val passedId = getSavedStateValue<Int>("creatorId")
        passedId?.let {
            getMarvelCreator(it)
        }
    }

    fun saveCreatorId(passedId: Int){
        setSavedStateValue("creatorId",passedId)
    }

    private fun getMarvelCreator(passedId: Int) {
        repository.getSingleCreator(passedId)
            .applySchedulersAndPostUIStates{
                _creator.postValue(
                    CreatorMapper().map(it.toData()!!)
                )
            }
    }

}









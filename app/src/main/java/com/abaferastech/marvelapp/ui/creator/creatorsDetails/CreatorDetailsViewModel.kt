package com.abaferastech.marvelapp.ui.creator.creatorsDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.abaferastech.marvelapp.data.model.result.Creators
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.ui.base.BaseViewModel
import com.abaferastech.marvelapp.ui.model.UIState
import io.reactivex.rxjava3.kotlin.addTo

class CreatorDetailsViewModel : BaseViewModel() {
    private val repository by lazy { MarvelRepository() }

    private val _creator = MutableLiveData<UIState<Creators>>()
    val creator: LiveData<UIState<Creators>> get() = _creator



    fun getMarvelCreator(id: Int) {
        repository.getSingleCreator(id)
            .applySchedulersAndPostUIStates(_creator::postValue)
    }

}









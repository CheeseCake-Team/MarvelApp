package com.abaferastech.marvelapp.ui.creators

import androidx.lifecycle.MutableLiveData
import com.abaferastech.marvelapp.data.model.Creators
import com.abaferastech.marvelapp.data.model.Series
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.ui.base.BaseViewModel

class CreatorsViewModel: BaseViewModel() {
    private val repository = MarvelRepository()

    val creators = MutableLiveData<Creators>()

}
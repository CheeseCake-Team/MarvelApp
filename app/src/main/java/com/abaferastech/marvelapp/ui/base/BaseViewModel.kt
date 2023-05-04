package com.abaferastech.marvelapp.ui.base

import androidx.lifecycle.ViewModel
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class BaseViewModel: ViewModel(){

    protected val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}
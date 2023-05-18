package com.abaferastech.marvelapp.ui.base

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.abaferastech.marvelapp.ui.model.UIState
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers

abstract class BaseViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {

    protected val compositeDisposable = CompositeDisposable()

    protected fun <T : Any> getSavedStateValue(key: String): T? {
        return savedStateHandle[key]
    }

    protected fun <T : Any> setSavedStateValue(key: String, value: T) {
        savedStateHandle[key] = value
    }

    protected fun <T : Any> Single<UIState<T>>.applySchedulersAndPostUIStates(
        postValue: (UIState<T>) -> Unit,
    ) {
        this.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
//            .doOnSubscribe { postValue(UIState.Loading) }
            .doOnError { error -> (postValue(UIState.Error(error.message.toString()))) }
            .subscribe(postValue)
            .addTo(compositeDisposable)
    }

    protected fun <T : Any> Observable<List<T>>.applySchedulersAndPostUIStates(
        postValue: (List<T>) -> Unit,
    ) {
        this.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { currentState ->
                postValue(currentState)
            }
            .addTo(compositeDisposable)
    }

    protected fun <T : Any> Single<T>.applySchedulersAndPostUIStates() {
        this.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
            .addTo(compositeDisposable)
    }

    protected fun Completable.applySchedulersAndPostUIStates() {
        this.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
            .addTo(compositeDisposable)
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}
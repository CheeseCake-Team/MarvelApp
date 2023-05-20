package com.abaferastech.marvelapp.ui.base

import androidx.lifecycle.ViewModel
import com.abaferastech.marvelapp.ui.model.UIState
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers

abstract class BaseViewModel : ViewModel() {

    protected val compositeDisposable = CompositeDisposable()

    protected fun <T : Any> Single<UIState<T>>.applySchedulersAndPostUIStates(
        postValue: (UIState<T>) -> Unit,
    ) {
        this
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { postValue(UIState.Loading) }
            .doOnError { error -> (postValue(UIState.Error(error.message.toString()))) }
            .subscribe(postValue)
            .addTo(compositeDisposable)
    }

    protected fun <T : Any> Observable<UIState<T>>.applySchedulersAndPostUIStates(
        postValue: (UIState<T>) -> Unit,
    ) {

        this.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { postValue(UIState.Loading) }
            .doOnError { error -> (postValue(UIState.Error(error.message.toString()))) }
            .subscribe(postValue)
            .addTo(compositeDisposable)
    }

    protected fun <T : Any> Observable<UIState<T>>.applySchedulersAndPostUIStates(
        onSuccess: (data: UIState<T>) -> Unit,
        onError: (e: Throwable) -> Unit,
    ) {
        this.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(onSuccess, onError)
            .addTo(compositeDisposable)
    }


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}
package com.abaferastech.marvelapp.ui.base

import androidx.lifecycle.ViewModel
import com.abaferastech.marvelapp.ui.model.UIState
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers

abstract class BaseViewModel : ViewModel() {

    protected val compositeDisposable = CompositeDisposable()

    protected fun <T : Any> Single<UIState<T>>.applySchedulersAndSubscribe(
        onPostValue: (UIState<T>) -> Unit,
    ) {

        this.subscribeOn(Schedulers.io())
            .doOnSubscribe { onPostValue(UIState.Loading) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(onPostValue) { onPostValue(UIState.Error(it.message.toString())) }
            .addTo(compositeDisposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

}
package com.abaferastech.marvelapp.ui.character.characterDetails

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.abaferastech.marvelapp.data.repository.MarvelRepository
import com.abaferastech.marvelapp.domain.models.Character
import com.abaferastech.marvelapp.ui.base.BaseViewModel
import com.abaferastech.marvelapp.ui.model.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    val repository: MarvelRepository,
    state: SavedStateHandle
) : BaseViewModel() {

    val characterArgs = state.let {
        CharacterDetailsFragmentArgs.fromSavedStateHandle(it)
    }

    var isFavouriteClicked = MutableLiveData<Boolean>()

    private val _character = MutableLiveData<UIState<Character>>()
    val character: LiveData<UIState<Character>> get() = _character

    private val _isCharacterFavourite = MutableLiveData(false)
    val isCharacterFavourite: LiveData<Boolean> get() = _isCharacterFavourite

    fun getSingleCharacter(passedId: Int? = null) {
        val characterId = passedId ?: characterArgs.characterId

        Completable.fromAction {
            repository.getSingleCharacter(characterId).doOnSuccess {
                when (it) {
                    is UIState.Success -> {
                        _isCharacterFavourite.postValue(it.toData()?.isFavourite)
                    }

                    else -> {
                        _isCharacterFavourite.postValue(false)
                    }
                }
            }
                .applySchedulersAndPostUIStates(_character::postValue)
        }
            .subscribeOn(Schedulers.io())
            .subscribe()
            .addTo(compositeDisposable)
    }

    fun insertCharacter() {
        Completable.fromAction {
            character.value?.toData()?.apply { isFavourite = true }
                ?.let { repository.insertCharacter(it) }
        }
            .subscribeOn(Schedulers.io())
            .subscribe {
                Log.e("save", "save Completed")
            }.addTo(compositeDisposable)
    }

    fun deleteCharacter() {
        Completable.fromAction {
            character.value?.toData()?.let { repository.deleteCharacter(it) }
        }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe {
                Log.e("save", "save Completed")
            }.addTo(compositeDisposable)

    }

    fun refresh() {
        val characterId = characterArgs.characterId
        getSingleCharacter(characterId)
    }

}
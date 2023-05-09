package com.abaferastech.marvelapp.ui.model

sealed class UIState<out T> {
    data class Success<T>(val data: T?) : UIState<T>()
    data class Error(val message: String) : UIState<Nothing>()
    object Loading : UIState<Nothing>()
    fun toData(): T? = if (this is Success) data else null
}
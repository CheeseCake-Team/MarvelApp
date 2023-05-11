package com.abaferastech.marvelapp.ui.model

sealed class SearchItem {
    data class Comics<T>(val items: List<T>)
    data class Characters<T>(val items: List<T>)
    data class Series<T>(val items: List<T>)
    data class Event<T>(val items: List<T>)
    data class Creator<T>(val items: List<T>)
}

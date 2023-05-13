package com.abaferastech.marvelapp.ui.home

sealed class HomeEvent {
    data class ClickCharacterEvent(val characterID: Int) : HomeEvent()
    data class ClickComicEvent(val comicID: Int) : HomeEvent()
    data class ClickSeriesEvent(val seriesID: Int) : HomeEvent()
}
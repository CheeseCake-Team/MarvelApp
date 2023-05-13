package com.abaferastech.marvelapp.ui.search

import com.abaferastech.marvelapp.ui.home.HomeEvent

sealed class SearchEvents {
    data class ClickCharacterEvent(val characterID: Int) : SearchEvents()
    data class ClickComicEvent(val comicID: Int) : SearchEvents()
    data class ClickSeriesEvent(val seriesID: Int) : SearchEvents()
    data class ClickEventEvent(val eventID: Int) : SearchEvents()

}
package com.abaferastech.marvelapp.ui.favourites


sealed class FavouritesEvents {
    data class ClickCharacterEvent(val characterID: Int) : FavouritesEvents()
    data class ClickComicEvent(val comicID: Int) : FavouritesEvents()
    data class ClickSeriesEvent(val seriesID: Int) : FavouritesEvents()
}
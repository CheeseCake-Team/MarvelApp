package com.abaferastech.marvelapp.ui.favourites

import com.abaferastech.marvelapp.domain.models.Character
import com.abaferastech.marvelapp.domain.models.Comic
import com.abaferastech.marvelapp.domain.models.Event
import com.abaferastech.marvelapp.domain.models.Series

sealed class FavouriteItems {
    data class FavouriteComics(val items: List<Comic>): FavouriteItems()
    data class FavouriteCharacters(val items: List<Character>): FavouriteItems()
    data class FavouriteSeries(val items: List<Series>): FavouriteItems()
    data class FavouriteEvents(val items: List<Event>): FavouriteItems()
}

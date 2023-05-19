package com.abaferastech.marvelapp.ui.model

import com.abaferastech.marvelapp.domain.models.Character
import com.abaferastech.marvelapp.domain.models.Comic
import com.abaferastech.marvelapp.domain.models.Event
import com.abaferastech.marvelapp.domain.models.Series

sealed class SearchItem(val itemsList: List<Any>) {
    data class ComicItem(val items: List<Comic>) : SearchItem(items)
    data class CharacterItem(val items: List<Character>) : SearchItem(items)
    data class SeriesItem(val items: List<Series>) : SearchItem(items)
    data class EventItem(val items: List<Event>) : SearchItem(items)
}

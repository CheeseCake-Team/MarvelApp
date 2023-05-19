package com.abaferastech.marvelapp.ui.model

import com.abaferastech.marvelapp.data.remote.response.EventDTO
import com.abaferastech.marvelapp.data.remote.response.ComicDTO
import com.abaferastech.marvelapp.data.remote.response.SeriesDTO
import com.abaferastech.marvelapp.data.remote.response.CharacterDTO
import com.abaferastech.marvelapp.domain.models.Character
import com.abaferastech.marvelapp.domain.models.Comic
import com.abaferastech.marvelapp.domain.models.Event
import com.abaferastech.marvelapp.domain.models.Series

sealed class SearchItem {
    data class ComicItem(val items: List<Comic>): SearchItem()
    data class CharacterItem(val items: List<Character>): SearchItem()
    data class SeriesItem(val items: List<Series>): SearchItem()
    data class EventItem(val items: List<Event>): SearchItem()
}

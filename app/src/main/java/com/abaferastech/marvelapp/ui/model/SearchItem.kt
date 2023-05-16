package com.abaferastech.marvelapp.ui.model

import com.abaferastech.marvelapp.data.remote.response.EventDTO
import com.abaferastech.marvelapp.data.remote.response.ComicDTO
import com.abaferastech.marvelapp.data.remote.response.SeriesDTO
import com.abaferastech.marvelapp.data.remote.response.CharacterDTO

sealed class SearchItem {
    data class Comic(val items: List<ComicDTO>): SearchItem()
    data class Character(val items: List<CharacterDTO>): SearchItem()
    data class Series(val items: List<SeriesDTO>): SearchItem()
    data class Event(val items: List<EventDTO>): SearchItem()
}

package com.abaferastech.marvelapp.ui.model

import com.abaferastech.marvelapp.data.remote.response.CharacterDTO
import com.abaferastech.marvelapp.data.remote.response.ComicDTO
import com.abaferastech.marvelapp.data.remote.response.EventDTO
import com.abaferastech.marvelapp.data.remote.response.SeriesDTO

sealed class SearchItem(val itemsList: List<Any>) {
    data class Comic(val items: List<ComicDTO>) : SearchItem(items)
    data class Character(val items: List<CharacterDTO>) : SearchItem(items)
    data class Series(val items: List<SeriesDTO>) : SearchItem(items)
    data class Event(val items: List<EventDTO>) : SearchItem(items)
}

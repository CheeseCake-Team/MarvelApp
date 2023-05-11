package com.abaferastech.marvelapp.ui.model

import com.abaferastech.marvelapp.data.model.result.Events
import com.abaferastech.marvelapp.data.model.result.Comics
import com.abaferastech.marvelapp.data.model.result.Characters

sealed class SearchItem {
    data class Comic(val items: List<Comics>): SearchItem()
    data class Character(val items: List<Characters>): SearchItem()
    data class Series(val items: List<com.abaferastech.marvelapp.data.model.result.Series>): SearchItem()
    data class Event(val items: List<Events>): SearchItem()
}

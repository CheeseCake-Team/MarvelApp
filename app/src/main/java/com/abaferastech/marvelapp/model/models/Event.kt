package com.abaferastech.marvelapp.model.models

import java.util.Date

data class Event(
    val id: Int?,
    val title: String?,
    val description: String?,
    val resourceURI: String?,
    val urls: List<Url>?,
    val modified: Date?,
    val start: Date?,
    val end: Date?,
    val thumbnail: Image?,
    val comics: ItemList<Comic>?,
    val stories: ItemList<Story>?,
    val series: ItemList<Series>?,
    val characters: ItemList<Character>?,
    val creators: ItemList<Creator>?,
    val next: Summary?,
    val previous: Summary?
)
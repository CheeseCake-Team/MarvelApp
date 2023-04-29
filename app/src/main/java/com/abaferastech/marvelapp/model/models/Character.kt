package com.abaferastech.marvelapp.model.a

import java.util.Date

data class Character(
    val id: Int?,
    val name: String?,
    val description: String?,
    val modified: Date?,
    val resourceURI: String?,
    val urls: Array<Url>?,
    val thumbnail: Image?,
    val series: ItemList<Series>?,
    val stories: ItemList<Story>?,
    val comics: ItemList<Comic>?,
    val events: ItemList<Event>?
)
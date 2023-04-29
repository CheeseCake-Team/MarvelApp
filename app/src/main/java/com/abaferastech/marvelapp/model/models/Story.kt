package com.abaferastech.marvelapp.model.a

import java.util.Date

data class Story(
    val id: Int?,
    val title: String?,
    val description: String?,
    val resourceURI: String?,
    val type: String?,
    val modified: Date?,
    val thumbnail: Image?,
    val comics: ItemList<Comic>?,
    val series: ItemList<Series>?,
    val events: ItemList<Event>?,
    val characters: ItemList<Character>?,
    val creators: ItemList<Creator>?,
    val originalissue: Summary?
)

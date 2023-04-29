package com.abaferastech.marvelapp.model.a

import java.util.Date

data class Series(
    val id: Int?,
    val title: String?,
    val description: String?,
    val resourceURI: String?,
    val urls: List<Url>?,
    val startYear: Int?,
    val endYear: Int?,
    val rating: String?,
    val modified: Date?,
    val thumbnail: Image?,
    val comics: ItemList<Comic>?,
    val stories: ItemList<Story>?,
    val events: ItemList<Event>?,
    val characters: ItemList<Character>?,
    val creators: ItemList<Creator>?,
    val next: Summary?,
    val previous: Summary?
)


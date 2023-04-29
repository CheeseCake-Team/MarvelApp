package com.abaferastech.marvelapp.model.models

import java.util.Date

data class Creator(
    val id: Int?,
    val firstName: String?,
    val middleName: String?,
    val lastName: String?,
    val suffix: String?,
    val fullName: String?,
    val modified: Date?,
    val resourceURI: String?,
    val urls: Array<Url>?,
    val thumbnail: Image?,
    val series: ItemList<Series>?,
    val stories: ItemList<Story>?,
    val comics: ItemList<Comic>?,
    val events: ItemList<Event>?
)
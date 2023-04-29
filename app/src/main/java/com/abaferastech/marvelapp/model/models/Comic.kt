package com.abaferastech.marvelapp.model.models

import java.util.*

data class Comic(
    val id: Int?,
    val digitalId: Int?,
    val title: String?,
    val issueNumber: Double?,
    val variantDescription: String?,
    val description: String?,
    val modified: Date?,
    val isbn: String?,
    val upc: String?,
    val diamondCode: String?,
    val ean: String?,
    val issn: String?,
    val format: String?,
    val pageCount: Int?,
    val textObjects: List<TextObject>?,
    val resourceURI: String?,
    val urls: List<Url>?,
    val series: Summary?,
    val variants: List<Summary>?,
    val collections: List<Summary>?,
    val collectedIssues: List<Summary>?,
    val dates: List<ComicDate>?,
    val prices: List<ComicPrice>?,
    val thumbnail: Image?,
    val images: List<Image>?,
    val creators: ItemList<Creator>?,
    val characters: ItemList<Character>?,
    val stories: ItemList<Story>?,
    val events: ItemList<Event>?


)

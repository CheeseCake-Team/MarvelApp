package com.abaferastech.marvelapp.model.a

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
    val textObjects: Array<TextObject>?,
    val resourceURI: String?,
    val urls: Array<Url>?,
    val series: Summary?,
    val variants: Array<Summary>?,
    val collections: Array<Summary>?,
    val collectedIssues: Array<Summary>?,
    val dates: Array<ComicDate>?,
    val prices: Array<ComicPrice>?,
    val thumbnail: Image?,
    val images: Array<Image>?,
    val creators: ItemList<Creator>?,
    val characters: ItemList<Character>?,
    val stories: ItemList<Story>?,
    val events: ItemList<Event>?


)

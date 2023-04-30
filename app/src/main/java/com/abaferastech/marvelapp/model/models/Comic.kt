package com.abaferastech.marvelapp.model.models

import java.util.*

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Comic(
    @SerializedName("id")
    @Expose
    val id: Int?,

    @SerializedName("digitalId")
    @Expose
    val digitalId: Int?,

    @SerializedName("title")
    @Expose
    val title: String?,

    @SerializedName("issueNumber")
    @Expose
    val issueNumber: Double?,

    @SerializedName("variantDescription")
    @Expose
    val variantDescription: String?,

    @SerializedName("description")
    @Expose
    val description: String?,

    @SerializedName("modified")
    @Expose
    val modified: Date?,

    @SerializedName("isbn")
    @Expose
    val isbn: String?,

    @SerializedName("upc")
    @Expose
    val upc: String?,

    @SerializedName("diamondCode")
    @Expose
    val diamondCode: String?,

    @SerializedName("ean")
    @Expose
    val ean: String?,

    @SerializedName("issn")
    @Expose
    val issn: String?,

    @SerializedName("format")
    @Expose
    val format: String?,

    @SerializedName("pageCount")
    @Expose
    val pageCount: Int?,

    @SerializedName("textObjects")
    @Expose
    val textObjects: List<TextObject>?,

    @SerializedName("resourceURI")
    @Expose
    val resourceURI: String?,

    @SerializedName("urls")
    @Expose
    val urls: List<Url>?,

    @SerializedName("series")
    @Expose
    val series: Summary?,

    @SerializedName("variants")
    @Expose
    val variants: List<Summary>?,

    @SerializedName("collections")
    @Expose
    val collections: List<Summary>?,

    @SerializedName("collectedIssues")
    @Expose
    val collectedIssues: List<Summary>?,

    @SerializedName("dates")
    @Expose
    val dates: List<ComicDate>?,

    @SerializedName("prices")
    @Expose
    val prices: List<ComicPrice>?,

    @SerializedName("thumbnail")
    @Expose
    val thumbnail: Image?,

    @SerializedName("images")
    @Expose
    val images: List<Image>?,

    @SerializedName("creators")
    @Expose
    val creators: ItemList<Creator>?,

    @SerializedName("characters")
    @Expose
    val characters: ItemList<Character>?,

    @SerializedName("stories")
    @Expose
    val stories: ItemList<Story>?,

    @SerializedName("events")
    @Expose
    val events: ItemList<Event>?
)


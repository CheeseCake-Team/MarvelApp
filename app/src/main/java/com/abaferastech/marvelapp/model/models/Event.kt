package com.abaferastech.marvelapp.model.models

import java.util.Date
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Event(
    @SerializedName("id")
    @Expose
    val id: Int?,

    @SerializedName("title")
    @Expose
    val title: String?,

    @SerializedName("description")
    @Expose
    val description: String?,

    @SerializedName("resourceURI")
    @Expose
    val resourceURI: String?,

    @SerializedName("urls")
    @Expose
    val urls: List<Url>?,

    @SerializedName("modified")
    @Expose
    val modified: Date?,

    @SerializedName("start")
    @Expose
    val start: Date?,

    @SerializedName("end")
    @Expose
    val end: Date?,

    @SerializedName("thumbnail")
    @Expose
    val thumbnail: Image?,

    @SerializedName("comics")
    @Expose
    val comics: ItemList<Comic>?,

    @SerializedName("stories")
    @Expose
    val stories: ItemList<Story>?,

    @SerializedName("series")
    @Expose
    val series: ItemList<Series>?,

    @SerializedName("characters")
    @Expose
    val characters: ItemList<Character>?,

    @SerializedName("creators")
    @Expose
    val creators: ItemList<Creator>?,

    @SerializedName("next")
    @Expose
    val next: Summary?,

    @SerializedName("previous")
    @Expose
    val previous: Summary?
)

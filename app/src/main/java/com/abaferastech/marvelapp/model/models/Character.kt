package com.abaferastech.marvelapp.model.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.Date

data class Character(
    @SerializedName("id")
    @Expose
    val id: Int? = 0,

    @SerializedName("name")
    @Expose
    val name: String?,

    @SerializedName("description")
    @Expose
    val description: String?,

    @SerializedName("modified")
    @Expose
    val modified: Date?,

    @SerializedName("resourceURI")
    @Expose
    val resourceURI: String?,

    @SerializedName("urls")
    @Expose
    val urls: List<Url>?,

    @SerializedName("thumbnail")
    @Expose
    val thumbnail: Image?,

    @SerializedName("series")
    @Expose
    val series: ItemList<Series>?,

    @SerializedName("stories")
    @Expose
    val stories: ItemList<Story>?,

    @SerializedName("comics")
    @Expose
    val comics: ItemList<Comic>?,

    @SerializedName("events")
    @Expose
    val events: ItemList<Event>?
)
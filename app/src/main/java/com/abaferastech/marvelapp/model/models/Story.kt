package com.abaferastech.marvelapp.model.models

import java.util.Date
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Story(
    @SerializedName("id")
    @Expose
    val id: Int?,

    @SerializedName("title")
    @Expose val title: String?,

    @SerializedName("description")
    @Expose
    val description: String?,

    @SerializedName("resourceURI")
    @Expose
    val resourceURI: String?,

    @SerializedName("type")
    @Expose
    val type: String?,

    @SerializedName("modified")
    @Expose
    val modified: Date?,

    @SerializedName("thumbnail")
    @Expose
    val thumbnail: Image?,

    @SerializedName("comics")
    @Expose
    val comics: ItemList<Comic>?,

    @SerializedName("series")
    @Expose
    val series: ItemList<Series>?,

    @SerializedName("events")
    @Expose
    val events: ItemList<Event>?,

    @SerializedName("characters")
    @Expose
    val characters: ItemList<Character>?,

    @SerializedName("creators")
    @Expose
    val creators: ItemList<Creator>?,

    @SerializedName("originalissue")
    @Expose
    val originalissue: Summary?
)

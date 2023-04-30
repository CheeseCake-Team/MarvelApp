package com.abaferastech.marvelapp.data.models

import java.util.Date
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Series(
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

    @SerializedName("startYear")
    @Expose
    val startYear: Int?,

    @SerializedName("endYear")
    @Expose
    val endYear: Int?,

    @SerializedName("rating")
    @Expose
    val rating: String?,

    @SerializedName("modified")
    @Expose
    val modified: Date?,

    @SerializedName("thumbnail")
    @Expose
    val thumbnail: Image?,

    @SerializedName("comics")
    @Expose
    val comics: ItemList<Comic>?,

    @SerializedName("stories")
    @Expose
    val stories: ItemList<Story>?,

    @SerializedName("events")
    @Expose
    val events: ItemList<Event>?,

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



package com.abaferastech.marvelapp.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.Date

data class Creator(
    @SerializedName("id")
    @Expose
    val id: Int?,

    @SerializedName("firstName")
    @Expose
    val firstName: String?,

    @SerializedName("middleName")
    @Expose
    val middleName: String?,

    @SerializedName("lastName")
    @Expose
    val lastName: String?,

    @SerializedName("suffix")
    @Expose
    val suffix: String?,

    @SerializedName("fullName")
    @Expose
    val fullName: String?,

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
    val thumbnail: Thumbnail?,

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
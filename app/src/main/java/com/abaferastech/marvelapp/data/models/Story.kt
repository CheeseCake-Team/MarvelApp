package com.abaferastech.marvelapp.data.models

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
    val thumbnail: Thumbnail?,

    @SerializedName("comics")
    @Expose
    val comics: NestedItem?,

    @SerializedName("series")
    @Expose
    val series: NestedItem?,

    @SerializedName("events")
    @Expose
    val events: NestedItem?,

    @SerializedName("characters")
    @Expose
    val characters: NestedItem?,

    @SerializedName("creators")
    @Expose
    val creators: NestedItem?,

    @SerializedName("originalissue")
    @Expose
    val originalissue: StoryItem?,

////    @SerializedName("results")
////    @Expose
////    val results: ItemList<Story>
////


)

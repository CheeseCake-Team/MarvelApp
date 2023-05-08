package com.abaferastech.marvelapp.data.model

import com.abaferastech.marvelapp.data.model.response.*
import com.google.gson.annotations.SerializedName

data class Characters(
    @SerializedName("stories") val stories: MarvelEntity<StoryItem>?,
    @SerializedName("creators") val creators: MarvelEntity<CreatorItem>?,
    @SerializedName("comics") val comics: MarvelEntity<ResponseItem>?,
    @SerializedName("series") val series: MarvelEntity<ResponseItem>?,
    @SerializedName("events") val events: MarvelEntity<ResponseItem>?,

    @SerializedName("id") val id: Int?,
    @SerializedName("modified") val modified: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("resourceURI") val resourceURI: String?,
    @SerializedName("thumbnail") val thumbnail: Thumbnail?,
    @SerializedName("urls") val urls: List<Url?>?
)
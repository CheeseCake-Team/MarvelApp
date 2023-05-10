package com.abaferastech.marvelapp.data.model.result

import com.abaferastech.marvelapp.data.model.response.*
import com.google.gson.annotations.SerializedName

data class Creators(
    @SerializedName("stories") val stories: MarvelEntity<StoryItem>?,
    @SerializedName("characters") val characters: MarvelEntity<ResponseItem>?,
    @SerializedName("comics") val comics: MarvelEntity<ResponseItem>?,
    @SerializedName("events") val events: MarvelEntity<ResponseItem>?,
    @SerializedName("series") val series: MarvelEntity<ResponseItem>?,

    @SerializedName("firstName") val firstName: String?,
    @SerializedName("fullName") val fullName: String?,
    @SerializedName("id") val id: Int?,
    @SerializedName("lastName") val lastName: String?,
    @SerializedName("middleName") val middleName: String?,
    @SerializedName("modified") val modified: String?,
    @SerializedName("resourceURI") val resourceURI: String?,
    @SerializedName("suffix") val suffix: String?,
    @SerializedName("thumbnail") val thumbnail: Thumbnail?,
    @SerializedName("urls") val urls: List<Url?>?
)
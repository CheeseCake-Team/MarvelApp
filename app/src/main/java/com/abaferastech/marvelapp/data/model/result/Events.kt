package com.abaferastech.marvelapp.data.model.result


import com.abaferastech.marvelapp.data.model.response.*
import com.google.gson.annotations.SerializedName

data class Events(
    @SerializedName("stories") val stories: MarvelEntity<StoryItem>?,
    @SerializedName("creators") val creators: MarvelEntity<CreatorItem>?,
    @SerializedName("characters") val characters: MarvelEntity<ResponseItem>?,
    @SerializedName("comics") val comics: MarvelEntity<ResponseItem>?,
    @SerializedName("series") val series: MarvelEntity<ResponseItem>?,

    @SerializedName("description") val description: String?,
    @SerializedName("end") val end: String?,
    @SerializedName("id") val id: Int?,
    @SerializedName("modified") val modified: String?,
    @SerializedName("next") val next: ResponseItem?,
    @SerializedName("previous") val previous: ResponseItem?,
    @SerializedName("resourceURI") val resourceURI: String?,
    @SerializedName("start") val start: String?,
    @SerializedName("thumbnail") val thumbnail: Thumbnail?,
    @SerializedName("title") val title: String?,
    @SerializedName("urls") val urls: List<Url?>?
)

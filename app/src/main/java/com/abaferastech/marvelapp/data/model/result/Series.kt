package com.abaferastech.marvelapp.data.model.result

import com.abaferastech.marvelapp.data.model.response.*
import com.google.gson.annotations.SerializedName

data class Series(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,

    @SerializedName("stories") val stories: MarvelEntity<StoryItem>?,
    @SerializedName("creators") val creators: MarvelEntity<CreatorItem>?,
    @SerializedName("characters") val characters: MarvelEntity<ResponseItem>?,
    @SerializedName("comics") val comics: MarvelEntity<ResponseItem>?,
    @SerializedName("events") val events: MarvelEntity<ResponseItem>?,

    @SerializedName("description") val description: String?,
    @SerializedName("resourceURI") val resourceURI: String,
    @SerializedName("urls") val urls: List<Url>,
    @SerializedName("startYear") val startYear: Int,
    @SerializedName("endYear") val endYear: Int,
    @SerializedName("rating") val rating: String?,
    @SerializedName("modified") val modified: String?,
    @SerializedName("thumbnail") val thumbnail: Thumbnail?,
    @SerializedName("next") val next: ResponseItem?,
    @SerializedName("previous") val previous: ResponseItem?
)

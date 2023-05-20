package com.abaferastech.marvelapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class SeriesDTO(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,

    @SerializedName("creators") val creators: MarvelResults<CreatorItem>?,
    @SerializedName("characters") val characters: MarvelResults<ResponseItem>?,
    @SerializedName("comics") val comics: MarvelResults<ResponseItem>?,
    @SerializedName("events") val events: MarvelResults<ResponseItem>?,

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

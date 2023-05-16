package com.abaferastech.marvelapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class CharacterDTO(
    @SerializedName("creators") val creators: MarvelResults<CreatorItem>?,
    @SerializedName("comics") val comics: MarvelResults<ResponseItem>?,
    @SerializedName("series") val series: MarvelResults<ResponseItem>?,
    @SerializedName("events") val events: MarvelResults<ResponseItem>?,

    @SerializedName("id") val id: Int?,
    @SerializedName("modified") val modified: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("resourceURI") val resourceURI: String?,
    @SerializedName("thumbnail") val thumbnail: Thumbnail?,
    @SerializedName("urls") val urls: List<Url?>?
)
package com.abaferastech.marvelapp.data.remote.response


import com.abaferastech.marvelapp.data.remote.response.CreatorItem
import com.abaferastech.marvelapp.data.remote.response.MarvelResults
import com.abaferastech.marvelapp.data.remote.response.ResponseItem
import com.abaferastech.marvelapp.data.remote.response.Thumbnail
import com.abaferastech.marvelapp.data.remote.response.Url
import com.google.gson.annotations.SerializedName

data class EventDTO(
    @SerializedName("creators") val creators: MarvelResults<CreatorItem>?,
    @SerializedName("characters") val characters: MarvelResults<ResponseItem>?,
    @SerializedName("comics") val comics: MarvelResults<ResponseItem>?,
    @SerializedName("series") val series: MarvelResults<ResponseItem>?,

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

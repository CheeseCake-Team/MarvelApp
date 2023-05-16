package com.abaferastech.marvelapp.data.remote.response

import com.abaferastech.marvelapp.data.remote.response.MarvelResults
import com.abaferastech.marvelapp.data.remote.response.ResponseItem
import com.abaferastech.marvelapp.data.remote.response.Thumbnail
import com.abaferastech.marvelapp.data.remote.response.Url
import com.google.gson.annotations.SerializedName

data class CreatorDTO(
    @SerializedName("characters") val characters: MarvelResults<ResponseItem>?,
    @SerializedName("comics") val comics: MarvelResults<ResponseItem>?,
    @SerializedName("events") val events: MarvelResults<ResponseItem>?,
    @SerializedName("series") val series: MarvelResults<ResponseItem>?,

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
package com.abaferastech.marvelapp.data.model.result

import android.provider.CalendarContract
import com.abaferastech.marvelapp.data.model.response.CreatorItem
import com.abaferastech.marvelapp.data.model.response.MarvelEntity
import com.abaferastech.marvelapp.data.model.response.ResponseItem
import com.abaferastech.marvelapp.data.model.response.Thumbnail
import com.google.gson.annotations.SerializedName

data class Stories(
    @SerializedName("characters") val characters: MarvelEntity<ResponseItem>?,
    @SerializedName("comics") val comics: MarvelEntity<ResponseItem>?,
    @SerializedName("creators") val creators: MarvelEntity<CreatorItem>?,
    @SerializedName("series") val series: MarvelEntity<ResponseItem>?,
    @SerializedName("events") val events: CalendarContract.Events?,

    @SerializedName("id") val id: Int?,
    @SerializedName("title") val title: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("originalIssue") val originalIssue: ResponseItem?,
    @SerializedName("modified") val modified: String?,
    @SerializedName("resourceURI") val resourceURI: String?,
    @SerializedName("thumbnail") val thumbnail: Thumbnail?,
    @SerializedName("type") val type: String?
)
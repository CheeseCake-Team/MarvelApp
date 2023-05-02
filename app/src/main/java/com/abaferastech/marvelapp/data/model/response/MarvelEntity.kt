package com.abaferastech.marvelapp.data.model.response

import com.google.gson.annotations.SerializedName

data class MarvelEntity<T>(
    @SerializedName("available") val available: Int,
    @SerializedName("collectionURI") val collectionURI: String,
    @SerializedName("items") val items: List<T>,
    @SerializedName("returned") val returned: Int
)

data class Url(
    @SerializedName("type") val type: String, @SerializedName("url") val url: String
)

data class Thumbnail(
    @SerializedName("path") val path: String, @SerializedName("extension") val extension: String
)
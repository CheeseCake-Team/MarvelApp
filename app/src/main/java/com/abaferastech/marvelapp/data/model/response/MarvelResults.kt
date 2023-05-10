package com.abaferastech.marvelapp.data.model.response

import com.google.gson.annotations.SerializedName

data class MarvelResults<T>(
    @SerializedName("available") val available: Int,
    @SerializedName("collectionURI") val collectionURI: String,
    @SerializedName("items") val items: List<T>,
    @SerializedName("returned") val returned: Int
)


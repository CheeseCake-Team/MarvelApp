package com.abaferastech.marvelapp.model.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ItemList<T> (
@SerializedName("available")
@Expose
val available: Int? = 0,
@SerializedName("collectionURI")
@Expose
val collectionURI: String? = "",
@SerializedName("items")
@Expose
val items: List<T>? = listOf(),
@SerializedName("returned")
@Expose
val returned: Int? = 0
)
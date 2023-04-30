package com.abaferastech.marvelapp.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ComicPrice(
    @SerializedName("price")
    @Expose
    val price: Double? = 0.0,
    @SerializedName("type")
    @Expose
    val type: String? = ""
)
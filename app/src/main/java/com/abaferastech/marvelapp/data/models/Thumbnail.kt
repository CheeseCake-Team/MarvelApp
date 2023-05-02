package com.abaferastech.marvelapp.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Thumbnail(
    @SerializedName("extension")
    @Expose
    val extension: String? = "",
    @SerializedName("path")
    @Expose
    val path: String? = ""
)














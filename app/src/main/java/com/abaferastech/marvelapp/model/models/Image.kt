package com.abaferastech.marvelapp.model.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("extension")
    @Expose
    val extension: String? = "",
    @SerializedName("path")
    @Expose
    val path: String? = ""
)














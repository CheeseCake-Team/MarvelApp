package com.abaferastech.marvelapp.model.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Summary(
    @SerializedName("name")
    @Expose
    val name: String?,
    @SerializedName("resourceURI")
    @Expose
    val resourceURI: String?
)
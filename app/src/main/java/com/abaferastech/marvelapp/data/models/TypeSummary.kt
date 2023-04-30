package com.abaferastech.marvelapp.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TypeSummary (
    @SerializedName("name")
    @Expose
    val name: String? = "",
    @SerializedName("resourceURI")
    @Expose
    val resourceURI: String? = "",
    @SerializedName("type")
    @Expose
    val type: String? = ""
)
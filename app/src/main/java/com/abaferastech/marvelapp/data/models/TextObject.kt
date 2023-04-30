package com.abaferastech.marvelapp.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TextObject(
    @SerializedName("language")
    @Expose
    val language: String? = "",
    @SerializedName("text")
    @Expose
    val text: String? = "",
    @SerializedName("type")
    @Expose
    val type: String? = ""
)
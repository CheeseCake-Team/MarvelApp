package com.abaferastech.marvelapp.model.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ComicDate(
    @SerializedName("date")
    @Expose
    val date: String? = "",
    @SerializedName("type")
    @Expose
    val type: String? = ""
)
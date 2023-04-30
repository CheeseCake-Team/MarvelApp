package com.abaferastech.marvelapp.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Error(
    @SerializedName("code")
    @Expose
    val code: Int?,
    @SerializedName("status")
    @Expose
    val status: String?
)

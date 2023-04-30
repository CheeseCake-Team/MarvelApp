package com.abaferastech.marvelapp.model.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RoleSummary(
    @SerializedName("name")
    @Expose
    val name: String?,
    @SerializedName("resourceURI")
    @Expose
    val resourceURI: String?,

    @SerializedName("role")
    @Expose
    val role: String?
)
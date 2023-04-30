package com.abaferastech.marvelapp.model.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Data<T>(
    @SerializedName("count")
    @Expose
    val count: Int?,
    @SerializedName("limit")
    @Expose
    val limit: Int?,
    @SerializedName("offset")
    @Expose
    val offset: Int?,
    @SerializedName("results")
    @Expose
    val results: List<T?>?,
    @SerializedName("total")
    @Expose
    val total: Int?
)
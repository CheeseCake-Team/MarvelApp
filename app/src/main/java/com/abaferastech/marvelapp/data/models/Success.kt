package com.abaferastech.marvelapp.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class Success<T>(
    @SerializedName("attributionHTML")
    @Expose
    val attributionHTML: String?,
    @SerializedName("attributionText")
    @Expose
    val attributionText: String?,
    @SerializedName("code")
    @Expose
    val code: Int?,
    @SerializedName("copyright")
    @Expose
    val copyright: String?,
    @SerializedName("data")
    @Expose
    val `data`: Data<T>?,
    @SerializedName("etag")
    @Expose
    val etag: String?,
    @SerializedName("status")
    @Expose
    val status: String?
)
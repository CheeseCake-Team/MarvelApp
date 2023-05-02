package com.abaferastech.marvelapp.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ApiResponse<T>(
    @SerializedName("data")
    @Expose
    val data: MarvelData<T>,
    val status: String

)

data class MarvelData<T>(
    @SerializedName("count")
    val count: Int?,

    @SerializedName("results")
    val results: List<T>,

    @SerializedName("total")
    val total: Int?

)

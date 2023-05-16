package com.abaferastech.marvelapp.data.remote.response

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("status") val status: String,
    @SerializedName("data") val data: DataContainer<T>
){
    data class DataContainer<T>(
        @SerializedName("offset") val offset: Int,
        @SerializedName("limit") val limit: Int,
        @SerializedName("total") val total: Int,
        @SerializedName("count") val count: Int,
        @SerializedName("results") val results: List<T>
    )
}






package com.abaferastech.marvelapp.data.model.response

import com.google.gson.annotations.SerializedName

data class MarvelBaseResponse<T>(
    @SerializedName("status") val status: String,
    @SerializedName("data") val data: MarvelData<T>
){
    data class MarvelData<T>(
        @SerializedName("offset") val offset: Int,
        @SerializedName("limit") val limit: Int,
        @SerializedName("total") val total: Int,
        @SerializedName("count") val count: Int,
        @SerializedName("results") val results: List<T>
    )
}






package com.abaferastech.marvelapp.data.models


import com.google.gson.annotations.SerializedName

data class NestedItem(

    @SerializedName("available")
    val available: Int?,

    @SerializedName("collectionURI")
    val collectionURI: String?,

    @SerializedName("items")
    val items: List<StoryItem?>?,

    @SerializedName("returned")
    val returned: Int?
)

data class StoryItem(
    @SerializedName("name")
    val name: String?,

    @SerializedName("resourceURI")
    val resourceURI: String?,

    @SerializedName("type")
    val type: String?,
)





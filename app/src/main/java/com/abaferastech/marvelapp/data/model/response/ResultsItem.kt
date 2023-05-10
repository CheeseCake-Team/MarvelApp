package com.abaferastech.marvelapp.data.model.response

import com.google.gson.annotations.SerializedName

data class CreatorItem(
    @SerializedName("resourceURI") val resourceURI: String,
    @SerializedName("name") val name: String,
    @SerializedName("role") val role: String
)

data class StoryItem(
    @SerializedName("resourceURI") val resourceURI: String,
    @SerializedName("name") val name: String,
    @SerializedName("type") val type: String
)

data class ResponseItem(
    @SerializedName("resourceURI") val resourceURI: String,
    @SerializedName("name") val name: String
)

package com.abaferastech.marvelapp.data.remote.response


import com.google.gson.annotations.SerializedName

data class ComicDTO(
    @SerializedName("creators") val creators: MarvelResults<CreatorItem>?,
    @SerializedName("characters") val characters: MarvelResults<ResponseItem>?,
    @SerializedName("series") val series: MarvelResults<ResponseItem>?,
    @SerializedName("events") val events: MarvelResults<ResponseItem>?,

    @SerializedName("collectedIssues") val collectedIssues: List<Any?>?,
    @SerializedName("collections") val collections: List<Any?>?,
    @SerializedName("dates") val dates: List<Date?>?,
    @SerializedName("description") val description: String?,
    @SerializedName("diamondCode") val diamondCode: String?,
//    @SerializedName("digitalId") val digitalId: Int?,
    @SerializedName("ean") val ean: String?,
    @SerializedName("format") val format: String?,
    @SerializedName("id") val id: Int?,
    @SerializedName("images") val images: List<Any?>?,
    @SerializedName("isbn") val isbn: String?,
    @SerializedName("issn") val issn: String?,
    @SerializedName("issueNumber") val issueNumber: Float?,
    @SerializedName("modified") val modified: String?,
    @SerializedName("pageCount") val pageCount: Int?,
    @SerializedName("prices") val prices: List<Price?>?,
    @SerializedName("resourceURI") val resourceURI: String?,
    @SerializedName("textObjects") val textObjects: List<Any?>?,
    @SerializedName("thumbnail") val thumbnail: Thumbnail?,
    @SerializedName("title") val title: String?,
    @SerializedName("upc") val upc: String?,
    @SerializedName("urls") val urls: List<Url?>?,
    @SerializedName("variantDescription") val variantDescription: String?,
    @SerializedName("variants") val variants: List<Variant?>?
)

data class Date(
    @SerializedName("date") val date: String?,
    @SerializedName("type") val type: String?
)

data class Price(
    @SerializedName("price") val price: Float?,
    @SerializedName("type") val type: String?
)

data class Variant(
    @SerializedName("name") val name: String?,
    @SerializedName("resourceURI") val resourceURI: String?
)

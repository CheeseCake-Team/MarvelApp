package com.abaferastech.marvelapp.data.models

import java.util.Date
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

//data class Series(
//    @SerializedName("id")
//    val id: Int,
//
//    @SerializedName("title")
//    val title: String,
//
//    @SerializedName("description")
//    val description: String,
//
//    @SerializedName("resourceURI")
//    val resourceURI: String,
//
//    @SerializedName("urls")
//    val urls: List<Url>,
//
//    @SerializedName("startYear")
//    val startYear: Int,
//
//    @SerializedName("endYear")
//    val endYear: Int,
//
//    @SerializedName("rating")
//    val rating: String,
//
//    @SerializedName("modified")
//    val modified: Date,
//
//    @SerializedName("thumbnail")
//    val thumbnail: Thumbnail,
//
////    @SerializedName("comics")
////    val comics: NestedItem,
//
//    @SerializedName("stories")
//    val stories: NestedItem,
//
////    @SerializedName("events")
////
////    val events: NestedItem,
////
////    @SerializedName("characters")
////
////    val characters: NestedItem,
////
////    @SerializedName("creators")
////
////    val creators: NestedItem,
//
//    @SerializedName("next")
//    val next: Summary,
//
//    @SerializedName("previous")
//    val previous: Summary
//)

data class Series(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String?,
    @SerializedName("resourceURI")
    val resourceURI: String,
    @SerializedName("urls")
    val urls: List<Url>,
    @SerializedName("startYear")
    val startYear: Int,
    @SerializedName("endYear")
    val endYear: Int,
    @SerializedName("rating")
    val rating: String?,
    @SerializedName("modified")
    val modified: Date?,
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail?,
    @SerializedName("stories")
    val stories: NestedItem?,
    @SerializedName("next")
    val next: Summary?,
    @SerializedName("previous")
    val previous: Summary?
)



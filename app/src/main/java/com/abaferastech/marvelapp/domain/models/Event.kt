package com.abaferastech.marvelapp.domain.models


data class Event(
    val id: Int,
    val title: String?,
    val description: String?,
    val modified: String?,
    val imageUri: String,
    var isFavourite: Boolean? = null

)

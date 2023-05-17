package com.abaferastech.marvelapp.domain.models

data class Comic(
    val id: Int,
    val title: String,
    val description: String?,
    val issueNumber: Float?,
    val price: Float?,
    val pageCount: Int?,
    val modified: String?,
    val imageUri: String?
)

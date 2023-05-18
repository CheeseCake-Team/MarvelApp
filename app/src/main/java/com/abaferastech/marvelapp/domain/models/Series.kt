package com.abaferastech.marvelapp.domain.models

data class Series(
    val id: Int,
    val title: String,
    val description: String?,
    val startYear: Int,
    val endYear: Int,
    val rating: String?,
    val modified: String?,
    val imageUri: String?
)

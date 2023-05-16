package com.abaferastech.marvelapp.data.domain.models

data class Character(
    val id: Int,
    val name: String,
    val description: String?,
    val modified: String?,
    val imageUri: String?
)

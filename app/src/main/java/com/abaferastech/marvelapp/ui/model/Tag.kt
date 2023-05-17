package com.abaferastech.marvelapp.ui.model

data class Tag<T>(
    val title: String,
    val id:Int,
    val ResourcesData: List<T>
)

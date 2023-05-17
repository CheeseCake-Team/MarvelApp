package com.abaferastech.marvelapp.ui.model

data class Tag<T>(
    val id:Int,
    val title: String,
    val ResourcesData: List<T>
)

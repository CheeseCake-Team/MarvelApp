package com.abaferastech.marvelapp.model.models

data class ItemList<T> (
    val available: Int?,
    val returned: Int?,
    val collectionURI: String?,
    val items: List<T>?
)
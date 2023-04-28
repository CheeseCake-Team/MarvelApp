package com.abaferastech.marvelapp.model.api

data class Data<T>(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: Array<T>
)
package com.abaferastech.marvelapp.model.api

data class Success<T>(
    override val code: Int,
    override val status: String,
    val etag: String,
    val data: Data<T>
) : ApiResponse()
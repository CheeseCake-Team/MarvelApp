package com.abaferastech.marvelapp.model.api

data class Success(
    override val code: Int,
    override val status: String,
    val etag: String,
    val data: Data<Any?>
) : ApiResponse()
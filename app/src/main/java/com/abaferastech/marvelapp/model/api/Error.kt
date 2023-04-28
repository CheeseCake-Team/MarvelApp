package com.abaferastech.marvelapp.model.api

data class Error(
    override val code: Int,
    override val status: String
) : ApiResponse()
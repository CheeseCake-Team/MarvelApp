package com.abaferastech.marvelapp.model.api

sealed class ApiResponse {
    abstract val code: Int
    abstract val status: String
}
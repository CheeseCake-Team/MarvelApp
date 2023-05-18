package com.abaferastech.marvelapp.domain.mapper

interface IMapper<I,O> {
    fun map(input: I):O
}
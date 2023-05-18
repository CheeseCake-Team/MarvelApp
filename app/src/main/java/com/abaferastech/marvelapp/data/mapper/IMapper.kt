package com.abaferastech.marvelapp.data.mapper

interface IMapper<changeFrom, changeTo> {
    fun map(input: List<changeFrom>): List<changeTo>
}
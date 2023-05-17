package com.abaferastech.marvelapp.domain.mapper

interface IMapper<changeFrom,changeTo> {
    fun map(input: changeFrom):changeTo
}
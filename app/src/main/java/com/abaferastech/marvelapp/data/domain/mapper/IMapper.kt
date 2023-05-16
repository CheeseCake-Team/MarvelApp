package com.abaferastech.marvelapp.data.domain.mapper

interface IMapper<changeFrom,changeTo> {
    fun map(input: changeFrom):changeTo
}
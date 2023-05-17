package com.abaferastech.marvelapp.data.domain.mapper.dtotodomain

import com.abaferastech.marvelapp.data.domain.mapper.IMapper
import com.abaferastech.marvelapp.data.domain.models.Comic
import com.abaferastech.marvelapp.data.local.entity.ComicEntity
import com.abaferastech.marvelapp.data.remote.response.ComicDTO

class ComicMapper : IMapper<ComicDTO, Comic> {
    override fun map(input: ComicDTO): Comic {
        return Comic(
            id = input.id!!,
            title = input.title!!,
            description = input.description,
            issueNumber = input.issueNumber,
            price = input.prices?.get(0)?.price,
            pageCount = input.pageCount,
            modified = input.modified,
            imageUri = "${input.thumbnail?.path}.${input.thumbnail?.extension}"
        )
    }
}
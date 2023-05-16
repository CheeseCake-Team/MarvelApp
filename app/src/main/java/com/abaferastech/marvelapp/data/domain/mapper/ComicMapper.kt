package com.abaferastech.marvelapp.data.domain.mapper

import com.abaferastech.marvelapp.data.domain.models.Comic
import com.abaferastech.marvelapp.data.remote.response.ComicDTO

class ComicMapper : IMapper<ComicDTO, Comic> {
    override fun map(input: ComicDTO): Comic {
        return Comic(
            input.id!!,
            input.title!!,
            input.description,
            input.issueNumber,
            input.prices?.get(0)?.price,
            input.pageCount,
            input.modified,
            input.thumbnail?.path+input.thumbnail?.extension
        )
    }
}
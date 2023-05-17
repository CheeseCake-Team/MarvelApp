package com.abaferastech.marvelapp.data.domain.mapper.todomain

import com.abaferastech.marvelapp.data.domain.mapper.IMapper
import com.abaferastech.marvelapp.data.domain.models.Comic
import com.abaferastech.marvelapp.data.local.entity.ComicEntity
import com.abaferastech.marvelapp.data.remote.response.ComicDTO

class ComicMapper : IMapper<ComicEntity, Comic> {
    override fun map(input: ComicEntity): Comic {
        return Comic(
            id = input.id,
            title = input.title,
            description = input.description,
            issueNumber = input.issueNumber,
            price = input.price,
            pageCount = input.pageCount,
            modified = input.modified,
            imageUri = input.imageUri
        )
    }
}
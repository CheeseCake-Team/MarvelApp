package com.abaferastech.marvelapp.domain.mapper

import com.abaferastech.marvelapp.domain.mapper.IMapper
import com.abaferastech.marvelapp.domain.models.Comic
import com.abaferastech.marvelapp.data.local.database.entity.ComicEntity
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
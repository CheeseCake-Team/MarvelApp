package com.abaferastech.marvelapp.data.mapper.entitiytodomain

import com.abaferastech.marvelapp.data.mapper.IMapper
import com.abaferastech.marvelapp.data.domain.models.Comic
import com.abaferastech.marvelapp.data.local.database.entity.ComicEntity

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
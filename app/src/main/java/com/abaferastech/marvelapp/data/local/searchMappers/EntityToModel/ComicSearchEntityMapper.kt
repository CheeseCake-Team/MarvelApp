package com.abaferastech.marvelapp.data.local.searchMappers.EntityToModel

import com.abaferastech.marvelapp.data.local.database.entity.search.ComicSearchEntity
import com.abaferastech.marvelapp.domain.mapper.IMapper
import com.abaferastech.marvelapp.domain.models.Comic
import javax.inject.Inject

class ComicSearchEntityMapper @Inject constructor() : IMapper<List<ComicSearchEntity>, List<Comic>> {
    override fun map(input: List<ComicSearchEntity>): List<Comic> {
        return input.map { comicSearchEntity ->
            Comic(
                id = comicSearchEntity.id,
                title = comicSearchEntity.title,
                description = null,
                issueNumber = comicSearchEntity.issueNumber,
                price = null,
                pageCount = null,
                modified = comicSearchEntity.modified,
                imageUri = comicSearchEntity.imageUri
            )
        }
    }
}

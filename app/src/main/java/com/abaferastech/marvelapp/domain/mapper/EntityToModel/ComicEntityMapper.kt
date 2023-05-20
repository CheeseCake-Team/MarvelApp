package com.abaferastech.marvelapp.domain.mapper.EntityToModel

import com.abaferastech.marvelapp.data.local.database.entity.ComicEntity
import com.abaferastech.marvelapp.domain.mapper.IMapper
import com.abaferastech.marvelapp.domain.models.Comic
import javax.inject.Inject

class ComicEntityMapper @Inject constructor(): IMapper<List<ComicEntity>, List<Comic>> {
    override fun map(input: List<ComicEntity>): List<Comic> {
        return input.map { comicEntity ->
            Comic(
                id = comicEntity.id,
                title = comicEntity.title,
                description = comicEntity.description,
                issueNumber = comicEntity.issueNumber,
                price = comicEntity.price,
                pageCount = comicEntity.pageCount,
                modified = comicEntity.modified,
                imageUri = comicEntity.imageUri
            )
        }
    }
}
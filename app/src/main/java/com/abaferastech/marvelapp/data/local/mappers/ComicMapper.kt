package com.abaferastech.marvelapp.data.local.mappers

import com.abaferastech.marvelapp.data.local.database.entity.ComicEntity
import com.abaferastech.marvelapp.data.local.database.entity.search.SearchQueryEntity
import com.abaferastech.marvelapp.data.remote.response.ComicDTO
import com.abaferastech.marvelapp.domain.mapper.IMapper

class ComicMapper(private val searchQueryEntity: SearchQueryEntity?) :
    IMapper<ComicDTO, ComicEntity> {
    override fun map(input: ComicDTO): ComicEntity {
        return ComicEntity(
            id = input.id!!,
            title = input.title!!,
            description = input.description,
            issueNumber = input.issueNumber,
            price = input.prices?.get(0)?.price,
            pageCount = input.pageCount,
            modified = input.modified,
            imageUri = input.thumbnail?.path + input.thumbnail?.extension
        )
    }
}
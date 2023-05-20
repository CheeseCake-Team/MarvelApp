package com.abaferastech.marvelapp.domain.mapper.DtoToEntity

import com.abaferastech.marvelapp.data.local.database.entity.ComicEntity
import com.abaferastech.marvelapp.data.remote.response.ComicDTO
import com.abaferastech.marvelapp.domain.mapper.IMapper
import javax.inject.Inject

class ComicDtoMapper  @Inject constructor(): IMapper<List<ComicDTO>, List<ComicEntity>> {
    override fun map(input: List<ComicDTO>): List<ComicEntity> {
        return input.map { comicDTO ->
            val thumbnailUrl = comicDTO.thumbnail?.let { thumbnail ->
                "${thumbnail.path}.${thumbnail.extension}"
            }
            ComicEntity(
                id = comicDTO.id ?: 0,
                title = comicDTO.title ?: "",
                description = comicDTO.description,
                issueNumber = comicDTO.issueNumber,
                price = comicDTO.prices?.firstOrNull()?.price,
                pageCount = comicDTO.pageCount,
                modified = comicDTO.modified,
                imageUri = thumbnailUrl
            )
        }
    }
}







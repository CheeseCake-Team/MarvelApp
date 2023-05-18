package com.abaferastech.marvelapp.domain.mapper

import com.abaferastech.marvelapp.domain.models.Comic
import com.abaferastech.marvelapp.data.remote.response.ComicDTO

class ComicDominMapper : IMapper<List<ComicDTO>, List<Comic>>{
    override fun map(input: List<ComicDTO>): List<Comic> {
        return input.map{ComicDTO->
            Comic(
                id = ComicDTO.id!!,
                title = ComicDTO.title!!,
                description = ComicDTO.description,
                issueNumber = ComicDTO.issueNumber,
                price = ComicDTO.prices?.get(0)?.price,
                pageCount = ComicDTO.pageCount,
                modified = ComicDTO.modified,
                imageUri = "${ComicDTO.thumbnail?.path}.${ComicDTO.thumbnail?.extension}"
            )
        }
    }
}
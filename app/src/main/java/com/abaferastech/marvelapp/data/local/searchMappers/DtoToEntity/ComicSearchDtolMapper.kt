package com.abaferastech.marvelapp.data.local.searchMappers.DtoToEntity

import com.abaferastech.marvelapp.domain.mapper.IMapper
import com.abaferastech.marvelapp.data.local.database.entity.search.ComicSearchEntity
import com.abaferastech.marvelapp.data.remote.response.ComicDTO
import javax.inject.Inject

class ComicSearchDtolMapper @Inject constructor(): IMapper< List<ComicDTO>, List< ComicSearchEntity>> {
    override fun map(input: List<ComicDTO>): List<ComicSearchEntity> {
        return input.map { ComicDto ->
            ComicSearchEntity(
            id = ComicDto.id!!,
            title = ComicDto.title!!,
            issueNumber = ComicDto.issueNumber,
            modified = ComicDto.modified,
            imageUri = ComicDto.thumbnail?.path + ComicDto.thumbnail?.extension
        )
    }}
}
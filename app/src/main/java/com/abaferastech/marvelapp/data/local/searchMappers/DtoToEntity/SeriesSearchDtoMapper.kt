package com.abaferastech.marvelapp.data.local.searchMappers.DtoToEntity

import com.abaferastech.marvelapp.domain.mapper.IMapper
import com.abaferastech.marvelapp.data.local.database.entity.search.SeriesSearchEntity
import com.abaferastech.marvelapp.data.remote.response.SeriesDTO
import javax.inject.Inject

class SeriesSearchDtoMapper  @Inject constructor(): IMapper<List<SeriesDTO>, List<SeriesSearchEntity>> {
    override fun map(input: List<SeriesDTO>): List<SeriesSearchEntity> {
        return input.map { SeriesDTO ->
            SeriesSearchEntity(
                id = SeriesDTO.id,
                title = SeriesDTO.title,
                startYear = SeriesDTO.startYear,
                modified = SeriesDTO.modified,
                imageUri = SeriesDTO.thumbnail?.path + SeriesDTO.thumbnail?.extension
            )
        }
    }
}
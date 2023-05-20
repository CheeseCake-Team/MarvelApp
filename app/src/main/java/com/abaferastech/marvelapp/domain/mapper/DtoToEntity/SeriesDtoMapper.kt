package com.abaferastech.marvelapp.domain.mapper.DtoToEntity

import com.abaferastech.marvelapp.data.local.database.entity.SeriesEntity
import com.abaferastech.marvelapp.data.remote.response.SeriesDTO
import com.abaferastech.marvelapp.domain.mapper.IMapper
import javax.inject.Inject

class SeriesDtoMapper @Inject constructor(): IMapper<List<SeriesDTO>, List<SeriesEntity>> {
    override fun map(input: List<SeriesDTO>): List<SeriesEntity> {
        return input.map { seriesDTO ->
            val thumbnailUrl = seriesDTO.thumbnail?.let { thumbnail ->
                "${thumbnail.path}.${thumbnail.extension}"
            }
            SeriesEntity(
                id = seriesDTO.id,
                title = seriesDTO.title,
                description = seriesDTO.description,
                startYear = seriesDTO.startYear,
                endYear = seriesDTO.endYear,
                rating = seriesDTO.rating,
                modified = seriesDTO.modified,
                imageUri = thumbnailUrl
            )
        }
    }
}
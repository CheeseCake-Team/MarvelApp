package com.abaferastech.marvelapp.domain.mapper

import com.abaferastech.marvelapp.domain.mapper.IMapper
import com.abaferastech.marvelapp.domain.models.Series
import com.abaferastech.marvelapp.data.local.database.entity.SeriesEntity
import com.abaferastech.marvelapp.data.remote.response.SeriesDTO
import javax.inject.Inject

class SeriesMapper @Inject constructor() : IMapper<List<SeriesDTO>, List<Series>> {
    override fun map(input: List<SeriesDTO>): List<Series> {
        return input.map { seriesDTO ->
            Series(
                id = seriesDTO.id,
                title = seriesDTO.title,
                description = seriesDTO.description,
                startYear = seriesDTO.startYear,
                endYear = seriesDTO.endYear,
                rating = seriesDTO.rating,
                modified = seriesDTO.modified,
                imageUri = "${seriesDTO.thumbnail?.path}.${seriesDTO.thumbnail?.extension}"
            )
        }
    }
}
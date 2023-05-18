package com.abaferastech.marvelapp.data.mapper.dtotodomain

import com.abaferastech.marvelapp.data.mapper.IMapper
import com.abaferastech.marvelapp.data.domain.models.Series
import com.abaferastech.marvelapp.data.local.database.entity.SeriesEntity
import com.abaferastech.marvelapp.data.remote.response.SeriesDTO

class SeriesMapper : IMapper<SeriesDTO, Series> {
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
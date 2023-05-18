package com.abaferastech.marvelapp.data.local.mappers

import com.abaferastech.marvelapp.domain.mapper.IMapper
import com.abaferastech.marvelapp.data.local.database.entity.SeriesEntity
import com.abaferastech.marvelapp.data.remote.response.SeriesDTO

class SeriesMapper : IMapper<SeriesDTO, SeriesEntity> {
    override fun map(input: List<SeriesDTO>): List<SeriesEntity> {
        return input.map { SeriesDTO ->
            SeriesEntity(
                id = SeriesDTO.id,
                title = SeriesDTO.title,
                description = SeriesDTO.description,
                startYear = SeriesDTO.startYear,
                endYear = SeriesDTO.endYear,
                rating = SeriesDTO.rating,
                modified = SeriesDTO.modified,
                imageUri = SeriesDTO.thumbnail?.path + SeriesDTO.thumbnail?.extension
            )
        }
    }
}
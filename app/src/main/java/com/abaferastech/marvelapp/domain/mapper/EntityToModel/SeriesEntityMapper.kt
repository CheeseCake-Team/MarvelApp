package com.abaferastech.marvelapp.domain.mapper.EntityToModel

import com.abaferastech.marvelapp.data.local.database.entity.SeriesEntity
import com.abaferastech.marvelapp.domain.mapper.IMapper
import com.abaferastech.marvelapp.domain.models.Series
import javax.inject.Inject

class SeriesEntityMapper @Inject constructor() : IMapper<List<SeriesEntity>, List<Series>> {
    override fun map(input: List<SeriesEntity>): List<Series> {
        return input.map { seriesEntity ->
            Series(
                id = seriesEntity.id,
                title = seriesEntity.title,
                description = seriesEntity.description,
                startYear = seriesEntity.startYear,
                endYear = seriesEntity.endYear,
                rating = seriesEntity.rating,
                modified = seriesEntity.modified,
                imageUri = seriesEntity.imageUri
            )
        }
    }
}

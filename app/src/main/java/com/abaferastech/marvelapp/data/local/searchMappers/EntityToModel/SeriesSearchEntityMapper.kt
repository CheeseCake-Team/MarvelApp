package com.abaferastech.marvelapp.data.local.searchMappers.EntityToModel

import com.abaferastech.marvelapp.data.local.database.entity.search.SeriesSearchEntity
import com.abaferastech.marvelapp.domain.mapper.IMapper
import com.abaferastech.marvelapp.domain.models.Series
import javax.inject.Inject

class SeriesSearchEntityMapper @Inject constructor(): IMapper<List<SeriesSearchEntity>, List<Series>> {
    override fun map(input: List<SeriesSearchEntity>): List<Series> {
        return input.map { seriesSearchEntity ->
            Series(
                id = seriesSearchEntity.id,
                title = seriesSearchEntity.title,
                description = null,
                startYear = seriesSearchEntity.startYear,
                endYear = null,
                rating = null,
                modified = seriesSearchEntity.modified,
                imageUri = seriesSearchEntity.imageUri
            )
        }
    }
}

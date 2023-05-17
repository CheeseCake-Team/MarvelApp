package com.abaferastech.marvelapp.data.mapper.dtotoentity

import com.abaferastech.marvelapp.data.mapper.IMapper
import com.abaferastech.marvelapp.data.local.database.entity.SeriesEntity
import com.abaferastech.marvelapp.data.remote.response.SeriesDTO

class SeriesMapper : IMapper<SeriesDTO, SeriesEntity> {
    override fun map(input: SeriesDTO): SeriesEntity {
        return SeriesEntity(
            id = input.id,
            title = input.title,
            description = input.description,
            startYear = input.startYear,
            endYear = input.endYear,
            rating = input.rating,
            modified = input.modified,
            imageUri = input.thumbnail?.path + input.thumbnail?.extension
        )
    }
}
package com.abaferastech.marvelapp.data.domain.mapper.todomain

import com.abaferastech.marvelapp.data.domain.mapper.IMapper
import com.abaferastech.marvelapp.data.domain.models.Series
import com.abaferastech.marvelapp.data.local.entity.SeriesEntity
import com.abaferastech.marvelapp.data.remote.response.SeriesDTO

class SeriesMapper : IMapper<SeriesEntity, Series> {
    override fun map(input: SeriesEntity): Series {
        return Series(
            id = input.id,
            title = input.title,
            description = input.description,
            startYear = input.startYear,
            endYear = input.endYear,
            rating = input.rating,
            modified = input.modified,
            imageUri = input.imageUri
        )
    }
}
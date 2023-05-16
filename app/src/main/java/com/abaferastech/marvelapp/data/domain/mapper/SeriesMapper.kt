package com.abaferastech.marvelapp.data.domain.mapper

import com.abaferastech.marvelapp.data.domain.models.Series
import com.abaferastech.marvelapp.data.remote.response.SeriesDTO

class SeriesMapper : IMapper<SeriesDTO, Series> {
    override fun map(input: SeriesDTO): Series {
        return Series(
            input.id,
            input.title,
            input.description,
            input.startYear,
            input.endYear,
            input.rating,
            input.modified,
            input.thumbnail?.path + input.thumbnail?.extension
        )
    }
}
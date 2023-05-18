package com.abaferastech.marvelapp.data.local.mappers

import com.abaferastech.marvelapp.data.local.database.entity.CharacterEntity
import com.abaferastech.marvelapp.data.local.database.entity.ComicEntity
import com.abaferastech.marvelapp.data.local.database.entity.SeriesEntity
import com.abaferastech.marvelapp.data.remote.response.CharacterDTO
import com.abaferastech.marvelapp.data.remote.response.ComicDTO
import com.abaferastech.marvelapp.data.remote.response.SeriesDTO
import com.abaferastech.marvelapp.domain.mapper.IMapper

class DTOCharacterListMapper : IMapper<List<CharacterDTO>, List<CharacterEntity>> {
    override fun map(input: List<CharacterDTO>): List<CharacterEntity> {
        return input.map {
            CharacterEntity(
                id = it.id!!,
                name = it.name!!,
                description = it.description,
                modified = it.modified,
                imageUri = "${it.thumbnail?.path}.${it.thumbnail?.extension}"
            )
        }
    }
}


class DTOComicListMapper : IMapper<List<ComicDTO>, List<ComicEntity>> {
    override fun map(input: List<ComicDTO>): List<ComicEntity> {
        return input.map {
            ComicEntity(
                id = it.id!!,
                title = it.title!!,
                description = it.description,
                issueNumber = it.issueNumber,
                price = it.prices?.get(0)?.price,
                pageCount = it.pageCount,
                modified = it.modified,
                imageUri = "${ it.thumbnail?.path }.${ it.thumbnail?.extension }"
            )
        }
    }
}


class DTOSeriesListMapper : IMapper<List<SeriesDTO>, List<SeriesEntity>> {
    override fun map(input: List<SeriesDTO>): List<SeriesEntity> {
        return input.map {
           SeriesEntity( id = it.id,
            title = it.title,
            description = it.description,
            startYear = it.startYear,
            endYear = it.endYear,
            rating = it.rating,
            modified = it.modified,
            imageUri = "${ it.thumbnail?.path }.${ it.thumbnail?.extension }")
        }
    }
}



class DTOSeriesListMapperr : IMapper<SeriesDTO, SeriesEntity> {
    override fun map(input: SeriesDTO): SeriesEntity{
        return SeriesEntity( id = input.id,
            title = input.title,
            description = input.description,
            startYear = input.startYear,
            endYear = input.endYear,
            rating = input.rating,
            modified = input.modified,
            imageUri = "${ input.thumbnail?.path }.${ input.thumbnail?.extension }")

    }
}


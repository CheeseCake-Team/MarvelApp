package com.abaferastech.marvelapp.domain.mapper

import com.abaferastech.marvelapp.data.local.database.entity.CharacterEntity
import com.abaferastech.marvelapp.data.local.database.entity.ComicEntity
import com.abaferastech.marvelapp.data.local.database.entity.SeriesEntity
import com.abaferastech.marvelapp.domain.models.Character
import com.abaferastech.marvelapp.domain.models.Comic
import com.abaferastech.marvelapp.domain.models.Series

class EntityCharacterListMapper : IMapper<List<CharacterEntity>, List<Character>> {
    override fun map(input: List<CharacterEntity>): List<Character> {
        return input.map {
            Character(
                id = it.id,
                name = it.name,
                description = it.description,
                modified = it.modified,
                imageUri = it.imageUri
            )
        }
    }
}
class EntityComicListMapper : IMapper<List<ComicEntity>, List<Comic>> {
    override fun map(input: List<ComicEntity>):  List<Comic>{
        return input.map {
            Comic(
                id = it.id,
                title = it.title,
                description = it.description,
                issueNumber = it.issueNumber,
                price = it.price,
                pageCount = it.pageCount,
                modified = it.modified,
                imageUri = it.imageUri
            )
        }
    }

}

class EntitySeriesListMapper : IMapper<List<SeriesEntity>, List<Series>> {
    override fun map(input: List<SeriesEntity>):  List<Series>{
        return input.map {
            Series(
                id = it.id,
                title = it.title,
                description = it.description,
                startYear = it.startYear,
                endYear = it.endYear,
                rating = it.rating,
                modified = it.modified,
                imageUri = it.imageUri
            )
        }
    }

}
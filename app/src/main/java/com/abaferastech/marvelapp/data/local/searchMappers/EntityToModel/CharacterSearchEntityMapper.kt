package com.abaferastech.marvelapp.data.local.searchMappers.EntityToModel

import com.abaferastech.marvelapp.data.local.database.entity.search.CharacterSearchEntity
import com.abaferastech.marvelapp.domain.mapper.IMapper
import com.abaferastech.marvelapp.domain.models.Character
class CharacterSearchEntityMapper : IMapper<List<CharacterSearchEntity>, List<Character>> {
    override fun map(input: List<CharacterSearchEntity>): List<Character> {
        return input.map { characterSearchEntity ->
            Character(
                id = characterSearchEntity.id,
                name = characterSearchEntity.name,
                description = null,
                modified = characterSearchEntity.modified,
                imageUri = characterSearchEntity.imageUri
            )
        }
    }
}

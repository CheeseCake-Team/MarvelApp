package com.abaferastech.marvelapp.domain.mapper.EntityToModel

import com.abaferastech.marvelapp.data.local.database.entity.CharacterEntity
import com.abaferastech.marvelapp.domain.mapper.IMapper
import com.abaferastech.marvelapp.domain.models.Character
import javax.inject.Inject

class CharacterEntityMapper @Inject constructor() : IMapper<List<CharacterEntity>, List<Character>>{
    override fun map(input: List<CharacterEntity>): List<Character> {
        return input.map { characterEntity ->
            Character(
                id = characterEntity.id,
                name = characterEntity.name,
                description = characterEntity.description,
                modified = characterEntity.modified,
                imageUri = characterEntity.imageUri
            )
        }
    }
}

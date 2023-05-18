package com.abaferastech.marvelapp.domain.mapper

import com.abaferastech.marvelapp.domain.models.Character
import com.abaferastech.marvelapp.data.local.database.entity.CharacterEntity

class CharacterDomainMapper : IMapper<CharacterEntity, Character> {
    override fun map(input: CharacterEntity): Character {
        return Character(
            id = input.id,
            name = input.name,
            description = input.description,
            modified = input.modified,
            imageUri = input.imageUri
        )
    }
}
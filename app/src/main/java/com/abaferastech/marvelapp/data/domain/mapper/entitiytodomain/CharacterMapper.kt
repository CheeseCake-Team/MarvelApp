package com.abaferastech.marvelapp.data.domain.mapper.entitiytodomain

import com.abaferastech.marvelapp.data.domain.mapper.IMapper
import com.abaferastech.marvelapp.data.domain.models.Character
import com.abaferastech.marvelapp.data.local.entity.CharacterEntity

class CharacterMapper : IMapper<CharacterEntity, Character> {
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
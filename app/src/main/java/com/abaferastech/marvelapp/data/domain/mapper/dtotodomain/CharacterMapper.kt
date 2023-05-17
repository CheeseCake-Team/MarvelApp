package com.abaferastech.marvelapp.data.domain.mapper.dtotodomain

import com.abaferastech.marvelapp.data.domain.mapper.IMapper
import com.abaferastech.marvelapp.data.domain.models.Character
import com.abaferastech.marvelapp.data.local.entity.CharacterEntity
import com.abaferastech.marvelapp.data.remote.response.CharacterDTO

class CharacterMapper : IMapper<CharacterDTO, Character> {
    override fun map(input: CharacterDTO): Character {
        return Character(
            id = input.id!!,
            name = input.name!!,
            description = input.description,
            modified = input.modified,
            imageUri = "${input.thumbnail?.path}.${input.thumbnail?.extension}"
        )
    }
}
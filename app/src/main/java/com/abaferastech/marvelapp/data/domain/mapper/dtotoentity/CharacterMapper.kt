package com.abaferastech.marvelapp.data.domain.mapper.dtotoentity

import com.abaferastech.marvelapp.data.domain.mapper.IMapper
import com.abaferastech.marvelapp.data.local.entity.CharacterEntity
import com.abaferastech.marvelapp.data.remote.response.CharacterDTO

class CharacterMapper : IMapper<CharacterDTO, CharacterEntity> {
    override fun map(input: CharacterDTO): CharacterEntity {
        return CharacterEntity(
            id = input.id!!,
            name = input.name!!,
            description = input.description,
            modified = input.modified,
            imageUri = input.thumbnail?.path + input.thumbnail?.extension
        )
    }
}
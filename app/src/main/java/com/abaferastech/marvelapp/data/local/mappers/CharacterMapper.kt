package com.abaferastech.marvelapp.data.local.mappers

import com.abaferastech.marvelapp.data.local.database.entity.CharacterEntity
import com.abaferastech.marvelapp.data.remote.response.CharacterDTO
import com.abaferastech.marvelapp.domain.mapper.IMapper

class CharacterMapper : IMapper<List<CharacterDTO>, List<CharacterEntity>> {
    override fun map(input: List<CharacterDTO>): List<CharacterEntity> {
        return input.map { CharacterDTO ->

            CharacterEntity(
                id = CharacterDTO.id!!,
                name = CharacterDTO.name!!,
                description = CharacterDTO.description,
                modified = CharacterDTO.modified,
                imageUri = CharacterDTO.thumbnail?.path + CharacterDTO.thumbnail?.extension
            )
        }
    }
}
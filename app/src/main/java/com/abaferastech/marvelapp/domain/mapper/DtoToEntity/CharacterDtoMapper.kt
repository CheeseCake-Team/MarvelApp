package com.abaferastech.marvelapp.domain.mapper.DtoToEntity

import com.abaferastech.marvelapp.data.local.database.entity.CharacterEntity
import com.abaferastech.marvelapp.data.remote.response.CharacterDTO
import com.abaferastech.marvelapp.domain.mapper.IMapper
import javax.inject.Inject

class CharacterDtoMapper  @Inject constructor(): IMapper<List<CharacterDTO>, List<CharacterEntity>> {
    override fun map(input: List<CharacterDTO>): List<CharacterEntity> {
        return input.map { characterDTO ->
            val thumbnailUrl = characterDTO.thumbnail?.let { thumbnail ->
                "${thumbnail.path}.${thumbnail.extension}"
            }
            CharacterEntity(
                id = characterDTO.id ?: 0,
                name = characterDTO.name ?: "",
                description = characterDTO.description,
                modified = characterDTO.modified,
                imageUri = thumbnailUrl
            )
        }
    }
}

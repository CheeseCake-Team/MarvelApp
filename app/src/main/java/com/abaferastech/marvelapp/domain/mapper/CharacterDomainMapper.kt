package com.abaferastech.marvelapp.domain.mapper

import com.abaferastech.marvelapp.domain.models.Character
import com.abaferastech.marvelapp.data.local.database.entity.CharacterEntity
import com.abaferastech.marvelapp.data.remote.response.CharacterDTO
import com.abaferastech.marvelapp.data.remote.response.SeriesDTO
import com.abaferastech.marvelapp.domain.models.Series

class CharacterDomainMapper : IMapper<List<CharacterDTO>, List<Character>> {
    override fun map(input: List<CharacterDTO>):List<Character> {
        return input.map { characterDTO ->
            Character(
            id = characterDTO.id!!,
            name = characterDTO.name!!,
            description = characterDTO.description,
            modified = characterDTO.modified,
            imageUri = "${characterDTO.thumbnail?.path}.${characterDTO.thumbnail?.extension}"
        )
    }
}


}
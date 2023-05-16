package com.abaferastech.marvelapp.data.domain.mapper

import com.abaferastech.marvelapp.data.domain.models.Character
import com.abaferastech.marvelapp.data.remote.response.CharacterDTO

class CharacterMapper:IMapper<CharacterDTO,Character> {
    override fun map(input: CharacterDTO): Character {
        return Character(
            input.id!!,
            input.name!!,
            input.description,
            input.modified,
            input.thumbnail?.path+input.thumbnail?.extension)
    }
}
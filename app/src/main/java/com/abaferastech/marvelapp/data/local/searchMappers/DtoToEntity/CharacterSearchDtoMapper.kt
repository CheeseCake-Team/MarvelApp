package com.abaferastech.marvelapp.data.local.searchMappers.DtoToEntity

import com.abaferastech.marvelapp.domain.mapper.IMapper
import com.abaferastech.marvelapp.data.local.database.entity.search.CharacterSearchEntity
import com.abaferastech.marvelapp.data.remote.response.CharacterDTO

class CharacterSearchDtoMapper : IMapper  <List<CharacterDTO>, List<CharacterSearchEntity>>{
    override fun map(input:List <CharacterDTO>):List <CharacterSearchEntity> {
        return input.map { CharacterDTO ->

            CharacterSearchEntity(
            id = CharacterDTO.id!!,
            name = CharacterDTO.name!!,
            modified = CharacterDTO.modified,
            imageUri = CharacterDTO.thumbnail?.path + CharacterDTO.thumbnail?.extension
        )
    }
}
}
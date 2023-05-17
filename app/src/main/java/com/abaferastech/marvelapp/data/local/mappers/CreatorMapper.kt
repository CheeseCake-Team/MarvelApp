package com.abaferastech.marvelapp.data.local.mappers

import com.abaferastech.marvelapp.domain.mapper.IMapper
import com.abaferastech.marvelapp.data.local.database.entity.CreatorEntity
import com.abaferastech.marvelapp.data.remote.response.CreatorDTO

class CreatorMapper: IMapper<CreatorDTO, CreatorEntity> {
    override fun map(input: CreatorDTO): CreatorEntity {
        return CreatorEntity(
            id = input.id,
            fullName = input.fullName,
            modified = input.modified,
            imageUri = input.thumbnail?.path + input.thumbnail?.extension
        )
    }
}
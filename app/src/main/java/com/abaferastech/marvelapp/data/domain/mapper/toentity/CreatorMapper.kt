package com.abaferastech.marvelapp.data.domain.mapper.toentity

import com.abaferastech.marvelapp.data.domain.mapper.IMapper
import com.abaferastech.marvelapp.data.domain.models.Creator
import com.abaferastech.marvelapp.data.local.entity.CreatorEntity
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
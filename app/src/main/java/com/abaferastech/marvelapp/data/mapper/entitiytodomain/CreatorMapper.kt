package com.abaferastech.marvelapp.data.mapper.entitiytodomain

import com.abaferastech.marvelapp.data.mapper.IMapper
import com.abaferastech.marvelapp.data.domain.models.Creator
import com.abaferastech.marvelapp.data.local.database.entity.CreatorEntity

class CreatorMapper: IMapper<CreatorEntity, Creator> {
    override fun map(input: CreatorEntity): Creator {
        return Creator(
            id = input.id,
            fullName = input.fullName,
            modified = input.modified,
            imageUri = input.imageUri
        )
    }
}
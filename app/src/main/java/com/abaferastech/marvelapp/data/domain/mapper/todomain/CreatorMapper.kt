package com.abaferastech.marvelapp.data.domain.mapper.todomain

import com.abaferastech.marvelapp.data.domain.mapper.IMapper
import com.abaferastech.marvelapp.data.domain.models.Creator
import com.abaferastech.marvelapp.data.local.entity.CreatorEntity
import com.abaferastech.marvelapp.data.remote.response.CreatorDTO

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
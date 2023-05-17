package com.abaferastech.marvelapp.data.domain.mapper.dtotodomain

import com.abaferastech.marvelapp.data.domain.mapper.IMapper
import com.abaferastech.marvelapp.data.domain.models.Creator
import com.abaferastech.marvelapp.data.local.entity.CreatorEntity
import com.abaferastech.marvelapp.data.remote.response.CreatorDTO

class CreatorMapper: IMapper<CreatorDTO, Creator> {
    override fun map(input: CreatorDTO): Creator {
        return Creator(
            id = input.id,
            fullName = input.fullName,
            modified = input.modified,
            imageUri = "${input.thumbnail?.path}.${input.thumbnail?.extension}"
        )
    }
}
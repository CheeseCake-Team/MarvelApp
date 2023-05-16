package com.abaferastech.marvelapp.data.domain.mapper

import com.abaferastech.marvelapp.data.domain.models.Creator
import com.abaferastech.marvelapp.data.remote.response.CreatorDTO

class CreatorMapper:IMapper<CreatorDTO,Creator> {
    override fun map(input: CreatorDTO): Creator {
        return Creator(
            input.id,
            input.fullName,
            input.modified,
            input.thumbnail?.path+input.thumbnail?.extension
        )
    }
}
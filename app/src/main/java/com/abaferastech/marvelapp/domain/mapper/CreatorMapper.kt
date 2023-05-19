package com.abaferastech.marvelapp.domain.mapper

import com.abaferastech.marvelapp.data.remote.response.CreatorDTO
import com.abaferastech.marvelapp.domain.models.Creator
import javax.inject.Inject

class CreatorMapper @Inject constructor() : IMapper<List<CreatorDTO>, List<Creator>> {
    override fun map(input: List<CreatorDTO>): List<Creator> {
        return input.map { CreatorDTO ->
            Creator(
                id = CreatorDTO.id,
                fullName = CreatorDTO.fullName,
                modified = CreatorDTO.modified,
                imageUri = "${CreatorDTO.thumbnail?.path}.${CreatorDTO.thumbnail?.extension}"
            )
        }
    }
}
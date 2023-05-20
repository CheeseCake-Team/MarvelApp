package com.abaferastech.marvelapp.data.local.searchMappers.DtoToEntity

import com.abaferastech.marvelapp.data.local.database.entity.search.EventSearchEntity
import com.abaferastech.marvelapp.data.remote.response.EventDTO
import com.abaferastech.marvelapp.domain.mapper.IMapper
import javax.inject.Inject

class EventSearchDtoMapper @Inject constructor() :
    IMapper<List<EventDTO>, List<EventSearchEntity>> {
    override fun map(input: List<EventDTO>): List<EventSearchEntity> {
        return input.map { eventDTO ->
            EventSearchEntity(
                id = eventDTO.id!!,
                title = eventDTO.title,
                imageUri = eventDTO.thumbnail?.path + eventDTO.thumbnail?.extension
            )
        }
    }
}





package com.abaferastech.marvelapp.data.local.mappers

import com.abaferastech.marvelapp.domain.mapper.IMapper
import com.abaferastech.marvelapp.data.local.database.entity.EventEntity
import com.abaferastech.marvelapp.data.remote.response.EventDTO

class EventMapper: IMapper<List<EventDTO>, List<EventEntity>> {
    override fun map(input: List<EventDTO>): List<EventEntity> {
        return input.map{eventDTO ->
            EventEntity(
                id = eventDTO.id!!,
                title = eventDTO.title,
                description = eventDTO.description,
                modified = eventDTO.modified,
                imageUri = eventDTO.thumbnail?.path + eventDTO.thumbnail?.extension
            )
        }
    }
}
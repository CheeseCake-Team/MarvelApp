package com.abaferastech.marvelapp.data.domain.mapper.toentity

import com.abaferastech.marvelapp.data.domain.mapper.IMapper
import com.abaferastech.marvelapp.data.domain.models.Event
import com.abaferastech.marvelapp.data.local.entity.EventEntity
import com.abaferastech.marvelapp.data.remote.response.EventDTO

class EventMapper: IMapper<EventDTO, EventEntity> {
    override fun map(input: EventDTO): EventEntity {
        return EventEntity(
            id = input.id!!,
            title = input.title,
            description = input.description,
            modified = input.modified,
            imageUri = input.thumbnail?.path + input.thumbnail?.extension
        )
    }
}
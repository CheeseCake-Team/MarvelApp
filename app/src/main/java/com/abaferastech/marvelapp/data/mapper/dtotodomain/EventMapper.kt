package com.abaferastech.marvelapp.data.mapper.dtotodomain

import com.abaferastech.marvelapp.data.mapper.IMapper
import com.abaferastech.marvelapp.data.domain.models.Event
import com.abaferastech.marvelapp.data.local.database.entity.EventEntity
import com.abaferastech.marvelapp.data.remote.response.EventDTO

class EventMapper: IMapper<EventDTO, Event> {
    override fun map(input: EventDTO): Event {
        return Event(
            id = input.id!!,
            title = input.title,
            description = input.description,
            modified = input.modified,
            imageUri = "${input.thumbnail?.path}.${input.thumbnail?.extension}"
        )
    }
}
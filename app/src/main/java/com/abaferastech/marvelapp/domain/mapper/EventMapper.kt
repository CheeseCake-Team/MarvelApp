package com.abaferastech.marvelapp.domain.mapper

import com.abaferastech.marvelapp.domain.mapper.IMapper
import com.abaferastech.marvelapp.domain.models.Event
import com.abaferastech.marvelapp.data.local.database.entity.EventEntity
import com.abaferastech.marvelapp.data.remote.response.EventDTO

class EventMapper: IMapper<EventEntity, Event> {
    override fun map(input: EventEntity): Event {
        return Event(
            id = input.id,
            title = input.title,
            description = input.description,
            modified = input.modified,
            imageUri = input.imageUri
        )
    }
}
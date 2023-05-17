package com.abaferastech.marvelapp.data.domain.mapper.entitiytodomain

import com.abaferastech.marvelapp.data.domain.mapper.IMapper
import com.abaferastech.marvelapp.data.domain.models.Event
import com.abaferastech.marvelapp.data.local.entity.EventEntity

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
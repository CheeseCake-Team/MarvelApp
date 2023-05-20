package com.abaferastech.marvelapp.data.local.searchMappers.EntityToModel

import com.abaferastech.marvelapp.data.local.database.entity.search.EventSearchEntity
import com.abaferastech.marvelapp.domain.mapper.IMapper
import com.abaferastech.marvelapp.domain.models.Event

class EventSearchEntityMapper : IMapper<List<EventSearchEntity>, List<Event>> {
    override fun map(input: List<EventSearchEntity>): List<Event> {
        return input.map { eventSearchEntity ->
            Event(
                id = eventSearchEntity.id,
                title = eventSearchEntity.title,
                description = null,
                modified = null,
                imageUri = eventSearchEntity.imageUri
            )
        }
    }
}

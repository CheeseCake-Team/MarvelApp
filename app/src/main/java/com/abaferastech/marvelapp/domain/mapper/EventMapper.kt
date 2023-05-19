package com.abaferastech.marvelapp.domain.mapper

import com.abaferastech.marvelapp.domain.mapper.IMapper
import com.abaferastech.marvelapp.domain.models.Event
import com.abaferastech.marvelapp.data.local.database.entity.EventEntity
import com.abaferastech.marvelapp.data.remote.response.EventDTO
import javax.inject.Inject

class EventMapper@Inject constructor() : IMapper<List<EventDTO>, List<Event>> {
    override fun map(input: List<EventDTO>): List<Event> {
        return input.map{eventDTO ->
            Event(
                id = eventDTO.id!!,
                title = eventDTO.title,
                description = eventDTO.description,
                modified = eventDTO.modified,
                imageUri = "${eventDTO.thumbnail?.path}.${eventDTO.thumbnail?.extension}"
            )
        }
    }

}
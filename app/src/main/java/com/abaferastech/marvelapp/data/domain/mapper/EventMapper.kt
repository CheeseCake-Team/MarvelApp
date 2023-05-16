package com.abaferastech.marvelapp.data.domain.mapper

import com.abaferastech.marvelapp.data.domain.models.Event
import com.abaferastech.marvelapp.data.remote.response.EventDTO

class EventMapper:IMapper<EventDTO,Event> {
    override fun map(input: EventDTO): Event {
        return Event(
            input.id!!,
            input.title,
            input.description,
            input.modified,
            input.thumbnail?.path+input.thumbnail?.extension
        )
    }
}
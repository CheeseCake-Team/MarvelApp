package com.abaferastech.marvelapp.ui.event.events

import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.data.remote.response.EventDTO
import com.abaferastech.marvelapp.domain.models.Event
import com.abaferastech.marvelapp.ui.base.BaseAdapter

class EventAdapter(listener: EventsInteractionListener) :
    BaseAdapter<EventDTO>(listener) {
    override val layoutId = R.layout.item_events
}


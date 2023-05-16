package com.abaferastech.marvelapp.ui.event.events

import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.data.remote.response.EventDTO
import com.abaferastech.marvelapp.ui.base.BaseAdapter

class EventAdapter(items: List<EventDTO>, listener: EventsInteractionListener) :
    BaseAdapter<EventDTO>(items, listener) {

    override val layoutID: Int
        get() = R.layout.item_events

}


package com.abaferastech.marvelapp.ui.event.events

import com.abaferastech.marvelapp.data.remote.response.EventDTO
import com.abaferastech.marvelapp.ui.base.BaseInteractionListener

interface EventsInteractionListener : BaseInteractionListener {
    fun onEventClick(event: EventDTO)
}
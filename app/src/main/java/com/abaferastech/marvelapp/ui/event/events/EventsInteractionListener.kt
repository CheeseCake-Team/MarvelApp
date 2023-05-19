package com.abaferastech.marvelapp.ui.event.events

import com.abaferastech.marvelapp.domain.models.Event
import com.abaferastech.marvelapp.ui.base.BaseInteractionListener

interface EventsInteractionListener : BaseInteractionListener {
    fun onEventClick(event: Event)
}
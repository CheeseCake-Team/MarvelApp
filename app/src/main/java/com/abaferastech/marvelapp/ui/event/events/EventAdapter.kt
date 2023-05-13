package com.abaferastech.marvelapp.ui.event.events

import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.data.model.result.Events
import com.abaferastech.marvelapp.ui.base.BaseAdapter
import com.abaferastech.marvelapp.ui.base.BaseInteractionListener

class EventAdapter(listener: EventsInteractionListener) :
    BaseAdapter<Events>( listener) {
    override val layoutId = R.layout.item_events
}


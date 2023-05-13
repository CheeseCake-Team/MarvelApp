package com.abaferastech.marvelapp.ui.event.events

import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.data.model.result.Events
import com.abaferastech.marvelapp.ui.base.BaseAdapter
import com.abaferastech.marvelapp.ui.base.BaseInteractionListener

class EventAdapter(items: List<Events>, listener: EventsInteractionListener) :
    BaseAdapter<Events>(items, listener) {

    override val layoutID: Int
        get() = R.layout.item_events

}


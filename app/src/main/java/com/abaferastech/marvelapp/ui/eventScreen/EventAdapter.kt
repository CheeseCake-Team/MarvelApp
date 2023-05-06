package com.abaferastech.marvelapp.ui.eventScreen

import com.abaferastech.marvelapp.BR
import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.data.model.Events
import com.abaferastech.marvelapp.databinding.FragmentEventsBinding
import com.abaferastech.marvelapp.databinding.ItemEventsBinding
import com.abaferastech.marvelapp.ui.base.BaseAdapter
import com.abaferastech.marvelapp.ui.base.BaseInteractionListener

class EventAdapter(items : List<Events> , listener: EventsInteractionListener) : BaseAdapter<Events>(items,listener) {

    override val layoutID: Int
        get() = R.layout.item_events

    override fun bindItemViewHolder(
        holder: BaseViewHolder,
        item: Events,
        listener: BaseInteractionListener?
    ) {
       (holder.binding as ItemEventsBinding).item = item
    }



}

interface EventsInteractionListener : BaseInteractionListener {

}
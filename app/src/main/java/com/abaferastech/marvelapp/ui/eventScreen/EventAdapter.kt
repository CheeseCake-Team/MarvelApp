package com.abaferastech.marvelapp.ui.eventScreen

import com.abaferastech.marvelapp.BR
import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.data.model.Events
import com.abaferastech.marvelapp.ui.base.BaseAdapter
import com.abaferastech.marvelapp.ui.base.BaseInteractionListener

class EventAdapter(items : List<Events> , listener: EventsInteractionListener) : BaseAdapter<Events>(items,listener) {

    override val layoutID: Int
        get() = R.layout.item_events


    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val currentItem = getItems()[position]
        when (holder) {

            is ItemViewHolder -> {

                holder.binding.setVariable(BR.item,currentItem)
            }
        }
    }

}

interface EventsInteractionListener : BaseInteractionListener {

}
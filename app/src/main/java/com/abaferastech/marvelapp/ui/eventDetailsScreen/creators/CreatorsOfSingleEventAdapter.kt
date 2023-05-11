package com.abaferastech.marvelapp.ui.eventDetailsScreen.creators

import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.data.model.Comics
import com.abaferastech.marvelapp.data.model.Creators
import com.abaferastech.marvelapp.ui.base.BaseAdapter
import com.abaferastech.marvelapp.ui.base.BaseInteractionListener

class CreatorsOfSingleEventAdapter(items: List<Creators>,
                                   listener: CreatorsInteractionListener) :
    BaseAdapter<Creators>(items, listener) {
    override val layoutID = R.layout.item_creators_horizontal


    interface CreatorsInteractionListener : BaseInteractionListener {
    }
}

package com.abaferastech.marvelapp.ui.creators

import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.data.model.Creators
import com.abaferastech.marvelapp.ui.base.BaseAdapter
import com.abaferastech.marvelapp.ui.base.BaseInteractionListener

class CreatorsAdapter(
    items : List<Creators>,
    listener : CreatorsInteractionListener?
) : BaseAdapter<Creators>(items, listener){
    override val layoutID: Int
        get() = R.layout.item_creators
}
interface CreatorsInteractionListener : BaseInteractionListener{
    fun onClickCreator(creator : Creators)
}
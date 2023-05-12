package com.abaferastech.marvelapp.ui.creator.creators

import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.data.model.result.Creators
import com.abaferastech.marvelapp.ui.base.BaseAdapter
import com.abaferastech.marvelapp.ui.base.BaseInteractionListener


class CreatorsAdapter(items: List<Creators>, creatorsInteractionListener: CreatorsInteractionListener) :
    BaseAdapter<Creators>(items, creatorsInteractionListener) {
    override val layoutID: Int
        get() = R.layout.item_creator
}
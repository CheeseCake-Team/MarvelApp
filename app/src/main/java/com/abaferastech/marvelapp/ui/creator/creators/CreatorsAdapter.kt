package com.abaferastech.marvelapp.ui.creator.creators

import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.data.model.result.Creators
import com.abaferastech.marvelapp.ui.base.BaseAdapter
import com.abaferastech.marvelapp.ui.base.BaseInteractionListener


class CreatorsAdapter(items: List<Creators>, creatorsInteractionListener: CreatorsInteractionListener) :
    BaseAdapter<Creators>(creatorsInteractionListener) {
    override val layoutId = R.layout.item_creator
}
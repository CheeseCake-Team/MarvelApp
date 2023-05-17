package com.abaferastech.marvelapp.ui.creator.creators

import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.domain.models.Creator
import com.abaferastech.marvelapp.ui.base.BaseAdapter


class CreatorsAdapter(creatorsInteractionListener: CreatorsInteractionListener) :
    BaseAdapter<Creator>(creatorsInteractionListener) {
    override val layoutId = R.layout.item_creator
}
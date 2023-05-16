package com.abaferastech.marvelapp.ui.creator.creators

import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.data.remote.response.CreatorDTO
import com.abaferastech.marvelapp.ui.base.BaseAdapter


class CreatorsAdapter(items: List<CreatorDTO>, creatorsInteractionListener: CreatorsInteractionListener) :
    BaseAdapter<CreatorDTO>(items, creatorsInteractionListener) {
    override val layoutID: Int
        get() = R.layout.item_creator
}
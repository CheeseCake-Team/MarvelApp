package com.abaferastech.marvelapp.ui.home

import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.data.model.Characters
import com.abaferastech.marvelapp.databinding.ItemHeaderBinding
import com.abaferastech.marvelapp.ui.base.BaseAdapter
import com.abaferastech.marvelapp.ui.base.BaseInteractionListener

class HeaderAdapter(items: List<Characters>):
    BaseAdapter<Characters>(items, null) {
    override val layoutID: Int
        get() = R.layout.item_header

}
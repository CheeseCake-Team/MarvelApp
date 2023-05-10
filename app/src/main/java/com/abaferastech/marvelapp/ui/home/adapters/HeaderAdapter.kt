package com.abaferastech.marvelapp.ui.home.adapters

import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.data.model.result.Characters
import com.abaferastech.marvelapp.ui.base.BaseAdapter

class HeaderAdapter(items: List<Characters>):
    BaseAdapter<Characters>(items, null) {
    override val layoutID: Int
        get() = R.layout.item_header

}
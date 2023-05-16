package com.abaferastech.marvelapp.ui.home.adapters

import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.data.remote.response.CharacterDTO
import com.abaferastech.marvelapp.ui.base.BaseAdapter

class HeaderAdapter(items: List<CharacterDTO>):
    BaseAdapter<CharacterDTO>(items, null) {
    override val layoutID: Int
        get() = R.layout.item_header

}
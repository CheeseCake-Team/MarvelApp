package com.abaferastech.marvelapp.ui.home.adapters

import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.data.remote.response.CharacterDTO
import com.abaferastech.marvelapp.ui.base.BaseAdapter
import com.abaferastech.marvelapp.ui.base.BaseInteractionListener

class HeaderAdapter :
    BaseAdapter<CharacterDTO>(object : BaseInteractionListener {}) {
    override val layoutId = R.layout.item_header

}
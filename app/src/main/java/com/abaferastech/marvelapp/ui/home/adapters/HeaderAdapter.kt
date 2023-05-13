package com.abaferastech.marvelapp.ui.home.adapters

import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.data.model.result.Characters
import com.abaferastech.marvelapp.ui.base.BaseAdapter
import com.abaferastech.marvelapp.ui.base.BaseInteractionListener

class HeaderAdapter(items: List<Characters>) :
    BaseAdapter<Characters>(object : BaseInteractionListener {}) {
    override val layoutId = R.layout.item_header

}
package com.abaferastech.marvelapp.ui.home.adapters

import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.data.model.result.Comics
import com.abaferastech.marvelapp.ui.base.BaseAdapter
import com.abaferastech.marvelapp.ui.base.BaseInteractionListener

class ComicAdapter( listener: ComicsInteractionListener) :
    BaseAdapter<Comics>( listener) {
    override val layoutId = R.layout.item_comic
}

interface ComicsInteractionListener : BaseInteractionListener {
    fun onClickComics(comics: Comics)
}

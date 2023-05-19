package com.abaferastech.marvelapp.ui.home.adapters

import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.data.remote.response.ComicDTO
import com.abaferastech.marvelapp.domain.models.Comic
import com.abaferastech.marvelapp.ui.base.BaseAdapter
import com.abaferastech.marvelapp.ui.base.BaseInteractionListener

class ComicAdapter(listener: ComicsInteractionListener) :
    BaseAdapter<Comic>(listener) {
    override val layoutId = R.layout.item_comic
}

interface ComicsInteractionListener : BaseInteractionListener {
    fun onClickComics(comics: Comic)
}

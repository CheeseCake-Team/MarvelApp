package com.abaferastech.marvelapp.ui.home.adapters

import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.data.remote.response.ComicDTO
import com.abaferastech.marvelapp.ui.base.BaseAdapter
import com.abaferastech.marvelapp.ui.base.BaseInteractionListener

class ComicAdapter(items: List<ComicDTO>, listener: ComicsInteractionListener) :
    BaseAdapter<ComicDTO>(items, listener) {
    override val layoutID = R.layout.item_comic
}

interface ComicsInteractionListener : BaseInteractionListener {
    fun onClickComics(comics: ComicDTO)
}

package com.abaferastech.marvelapp.ui.comic.comics

import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.data.remote.response.ComicDTO
import com.abaferastech.marvelapp.ui.base.BaseAdapter

class ComicsAdapter(items: List<ComicDTO>, listener: ComicsInteractionListener) :
    BaseAdapter<ComicDTO>(items, listener) {
    override val layoutID = R.layout.item_comic_horizontal
}




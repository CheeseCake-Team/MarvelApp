package com.abaferastech.marvelapp.ui.comic.comicsGrid

import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.data.remote.response.ComicDTO
import com.abaferastech.marvelapp.ui.base.BaseAdapter
import com.abaferastech.marvelapp.ui.comic.comics.ComicsInteractionListener

class ComicsGridAdapter(items: List<ComicDTO>, listener: ComicsInteractionListener) :
    BaseAdapter<ComicDTO>(items, listener) {
    override val layoutID = R.layout.item_comic_grid
}



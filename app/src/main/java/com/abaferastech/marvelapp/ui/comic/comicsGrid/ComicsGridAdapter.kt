package com.abaferastech.marvelapp.ui.comic.comicsGrid

import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.domain.models.Comic
import com.abaferastech.marvelapp.ui.base.BaseAdapter
import com.abaferastech.marvelapp.ui.comic.comics.ComicsInteractionListener

class ComicsGridAdapter(listener: ComicsInteractionListener) :
    BaseAdapter<Comic>(listener) {
    override val layoutId = R.layout.item_comic_grid
}



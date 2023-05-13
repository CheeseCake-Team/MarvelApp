package com.abaferastech.marvelapp.ui.comic.comicsGrid

import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.data.model.result.Comics
import com.abaferastech.marvelapp.ui.base.BaseAdapter
import com.abaferastech.marvelapp.ui.comic.comics.ComicsInteractionListener

class ComicsGridAdapter(items: List<Comics>, listener: ComicsInteractionListener) :
    BaseAdapter<Comics>(listener) {
    override val layoutId = R.layout.item_comic_grid
}



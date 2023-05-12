package com.abaferastech.marvelapp.ui.comic.comics

import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.data.model.result.Comics
import com.abaferastech.marvelapp.ui.base.BaseAdapter
import com.abaferastech.marvelapp.ui.base.BaseInteractionListener

class ComicsAdapter(items: List<Comics>, listener: ComicsInteractionListener) :
    BaseAdapter<Comics>(items, listener) {
    override val layoutID = R.layout.item_comic_horizontal
}




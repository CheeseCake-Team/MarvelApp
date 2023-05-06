package com.abaferastech.marvelapp.ui.comics

import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.data.model.Comics
import com.abaferastech.marvelapp.ui.base.BaseAdapter
import com.abaferastech.marvelapp.ui.base.BaseInteractionListener

class ComicsAdapter(
    items : List<Comics>, listener : ComicsInteractionListener?)
    : BaseAdapter<Comics>(items, listener) {
    override val layoutID: Int
        get() = R.layout.item_comics
}
interface ComicsInteractionListener : BaseInteractionListener {
    fun onClickComic(comics : Comics)
}
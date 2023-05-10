package com.abaferastech.marvelapp.ui.characters

import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.data.model.result.Characters
import com.abaferastech.marvelapp.ui.base.BaseAdapter

class ViewAllCharactersAdapter(items: List<Characters>, listener: CharactersInteractionListener) :
    BaseAdapter<Characters>(items, listener) {
    override val layoutID: Int
        get() = R.layout.item_view_all_characters


}

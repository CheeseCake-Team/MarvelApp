package com.abaferastech.marvelapp.ui.character.characters

import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.data.model.result.Characters
import com.abaferastech.marvelapp.ui.base.BaseAdapter


class CharactersAdapter(items: List<Characters>, charactersInteractionListener: CharactersInteractionListener) :
    BaseAdapter<Characters>(items, charactersInteractionListener) {
    override val layoutID: Int
        get() = R.layout.item_character
}
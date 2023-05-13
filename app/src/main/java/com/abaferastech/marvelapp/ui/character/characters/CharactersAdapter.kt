package com.abaferastech.marvelapp.ui.character.characters

import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.data.model.result.Characters
import com.abaferastech.marvelapp.ui.base.BaseAdapter


class CharactersAdapter(charactersInteractionListener: CharactersInteractionListener) :
    BaseAdapter<Characters>( charactersInteractionListener) {
    override val layoutId = R.layout.item_character
}
package com.abaferastech.marvelapp.ui.home.adapters

import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.domain.models.Character
import com.abaferastech.marvelapp.ui.base.BaseAdapter
import com.abaferastech.marvelapp.ui.character.characters.CharactersInteractionListener


class CharactersAdapter(charactersInteractionListener: CharactersInteractionListener) :
    BaseAdapter<Character>(charactersInteractionListener) {
    override val layoutId = R.layout.item_character
}
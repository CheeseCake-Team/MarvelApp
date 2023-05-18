package com.abaferastech.marvelapp.ui.home.adapters

import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.data.remote.response.CharacterDTO
import com.abaferastech.marvelapp.ui.base.BaseAdapter
import com.abaferastech.marvelapp.ui.character.characters.CharactersInteractionListener


class CharactersAdapter(charactersInteractionListener: CharactersInteractionListener) :
    BaseAdapter<CharacterDTO>(charactersInteractionListener) {
    override val layoutId = R.layout.item_character
}
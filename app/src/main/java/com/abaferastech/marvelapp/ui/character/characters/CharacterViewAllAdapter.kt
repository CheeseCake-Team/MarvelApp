package com.abaferastech.marvelapp.ui.character.characters

import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.data.remote.response.CharacterDTO
import com.abaferastech.marvelapp.ui.base.BaseAdapter


class CharacterViewAllAdapter(
    charactersInteractionListener: CharactersInteractionListener
) :BaseAdapter<CharacterDTO>(charactersInteractionListener) {
    override val layoutId: Int
        get() = R.layout.item_view_all_characters
}
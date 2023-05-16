package com.abaferastech.marvelapp.ui.character.characters

import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.data.remote.response.CharacterDTO
import com.abaferastech.marvelapp.ui.base.BaseAdapter


class CharactersAdapter(items: List<CharacterDTO>, charactersInteractionListener: CharactersInteractionListener) :
    BaseAdapter<CharacterDTO>(items, charactersInteractionListener) {
    override val layoutID: Int
        get() = R.layout.item_character
}
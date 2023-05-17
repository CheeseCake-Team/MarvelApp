package com.abaferastech.marvelapp.ui.character.characters

import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.data.remote.response.CharacterDTO
import com.abaferastech.marvelapp.domain.models.Character
import com.abaferastech.marvelapp.ui.base.BaseAdapter


class CharactersAdapter(charactersInteractionListener: CharactersInteractionListener) :
    BaseAdapter<CharacterDTO>(charactersInteractionListener) {
    override val layoutId = R.layout.item_character
}
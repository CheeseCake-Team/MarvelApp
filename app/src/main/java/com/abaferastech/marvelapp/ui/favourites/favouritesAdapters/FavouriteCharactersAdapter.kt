package com.abaferastech.marvelapp.ui.favourites.favouritesAdapters

import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.domain.models.Character
import com.abaferastech.marvelapp.ui.base.BaseAdapter
import com.abaferastech.marvelapp.ui.base.BaseInteractionListener
import com.abaferastech.marvelapp.ui.character.characters.CharactersInteractionListener

class FavouriteCharactersAdapter(listener: CharactersInteractionListener) :
    BaseAdapter<Character>(object : CharactersInteractionListener {
        override fun onClickCharacter(character: Character) {}
    }) {

    override val layoutId = R.layout.item_view_all_characters

}
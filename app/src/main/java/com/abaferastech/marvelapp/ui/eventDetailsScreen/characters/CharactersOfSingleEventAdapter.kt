package com.abaferastech.marvelapp.ui.eventDetailsScreen.characters

import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.data.model.Characters
import com.abaferastech.marvelapp.ui.base.BaseAdapter
import com.abaferastech.marvelapp.ui.base.BaseInteractionListener
import com.abaferastech.marvelapp.ui.characters.CharactersInteractionListener

class CharactersOfSingleEventAdapter(items: List<Characters>,
                                     listener: CharactersInteractionListener
) :
    BaseAdapter<Characters>(items, listener) {
    override val layoutID = R.layout.item_characters_horizontal

}

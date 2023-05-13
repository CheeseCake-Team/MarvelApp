package com.abaferastech.marvelapp.ui.eventDetails.characters

import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.data.model.result.Characters
import com.abaferastech.marvelapp.ui.base.BaseAdapter
import com.abaferastech.marvelapp.ui.base.BaseInteractionListener
import com.abaferastech.marvelapp.ui.character.characters.CharactersInteractionListener

class CharactersOfSingleEventAdapter(items: List<Characters>,
                                     listener: CharactersInteractionListener
) :
    BaseAdapter<Characters>(items, listener) {
    override val layoutID = R.layout.item_characters_horizontal

}

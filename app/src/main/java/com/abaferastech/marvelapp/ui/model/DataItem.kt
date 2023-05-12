package com.abaferastech.marvelapp.ui.model

import com.abaferastech.marvelapp.data.model.result.Characters
import com.abaferastech.marvelapp.data.model.result.Comics
import com.abaferastech.marvelapp.data.model.result.Series
import com.abaferastech.marvelapp.ui.character.characters.CharactersInteractionListener
import com.abaferastech.marvelapp.ui.home.adapters.ComicsInteractionListener
import com.abaferastech.marvelapp.ui.home.adapters.SeriesInteractionListener

sealed class DataItem(val rank: Int) {

    data class ComicsTagItem(
        val tag: Tag<Comics>,
        val interactionListener: ComicsInteractionListener
    ) : DataItem(2)

    data class CharacterTagItem(
        val tag: Tag<Characters>,
        val interactionListener: CharactersInteractionListener
    ) : DataItem(1)

    data class SeriesTagItem(
        val tag: Tag<Series>,
        val interactionListener: SeriesInteractionListener
    ) : DataItem(3)


    data class HeaderItem(val items: List<Characters>) : DataItem(0)
}
//    data class HeaderDetailsItem(val items: Comics,) : DataItem()
//
//    data class TabItem(val items: CharacterDetailsFragment, ) : DataItem()


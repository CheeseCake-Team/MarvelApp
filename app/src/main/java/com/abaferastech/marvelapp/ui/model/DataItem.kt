package com.abaferastech.marvelapp.ui.model

import com.abaferastech.marvelapp.data.model.result.Characters
import com.abaferastech.marvelapp.data.model.result.Comics
import com.abaferastech.marvelapp.data.model.result.Series
import com.abaferastech.marvelapp.ui.characterDetails.CharacterFragment
import com.abaferastech.marvelapp.ui.characters.CharactersInteractionListener
import com.abaferastech.marvelapp.ui.home.adapters.ComicsInteractionListener
import com.abaferastech.marvelapp.ui.home.adapters.SeriesInteractionListener

sealed class DataItem() {

    data class ComicsTagItem(
        val tag: Tag<Comics>,
        val rank: Int,
        val interactionListener: ComicsInteractionListener
    ) :
        DataItem()

    data class CharacterTagItem(
        val tag: Tag<Characters>, val rank: Int,
        val interactionListener: CharactersInteractionListener
    ) : DataItem()

    data class SeriesTagItem(
        val tag: Tag<Series>, val rank: Int,
        val interactionListener: SeriesInteractionListener
    ) : DataItem()


    data class HeaderItem(
        val items: List<Characters>,
        val rank: Int,
    ) : DataItem()

    data class HeaderDetailsItem(
        val items: Comics,
        val rank: Int,
    ) : DataItem()

    data class TabItem(
        val items: CharacterFragment,
        val rank: Int,
    ) : DataItem()
}

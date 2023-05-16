package com.abaferastech.marvelapp.ui.model

import com.abaferastech.marvelapp.data.remote.response.CharacterDTO
import com.abaferastech.marvelapp.data.remote.response.ComicDTO
import com.abaferastech.marvelapp.data.remote.response.SeriesDTO
import com.abaferastech.marvelapp.ui.character.characters.CharactersInteractionListener
import com.abaferastech.marvelapp.ui.home.adapters.ComicsInteractionListener
import com.abaferastech.marvelapp.ui.home.adapters.SeriesInteractionListener

sealed class DataItem(val rank: Int) {

    data class ComicsTagItem(
        val tag: Tag<ComicDTO>,
        val interactionListener: ComicsInteractionListener
    ) : DataItem(2)

    data class CharacterTagItem(
        val tag: Tag<CharacterDTO>,
        val interactionListener: CharactersInteractionListener
    ) : DataItem(1)

    data class SeriesTagItem(
        val tag: Tag<SeriesDTO>,
        val interactionListener: SeriesInteractionListener
    ) : DataItem(3)


    data class HeaderItem(val items: List<CharacterDTO>) : DataItem(0)
}
//    data class HeaderDetailsItem(val items: Comics,) : DataItem()
//
//    data class TabItem(val items: CharacterDetailsFragment, ) : DataItem()


package com.abaferastech.marvelapp.ui.model

import com.abaferastech.marvelapp.data.remote.response.CharacterDTO
import com.abaferastech.marvelapp.data.remote.response.ComicDTO
import com.abaferastech.marvelapp.data.remote.response.SeriesDTO
import com.abaferastech.marvelapp.domain.models.Character
import com.abaferastech.marvelapp.domain.models.Comic
import com.abaferastech.marvelapp.domain.models.Series
import com.abaferastech.marvelapp.ui.character.characters.CharactersInteractionListener
import com.abaferastech.marvelapp.ui.home.adapters.ComicsInteractionListener
import com.abaferastech.marvelapp.ui.home.adapters.SeriesInteractionListener

sealed class DataItem(val rank: Int) {

    data class HeaderItem(val items: List<Character>) : DataItem(0)
    data class CharacterTagItem(
        val tag: Tag<Character>,
        val interactionListener: CharactersInteractionListener
    ) : DataItem(1)

    data class ComicsTagItem(
        val tag: Tag<Comic>,
        val interactionListener: ComicsInteractionListener
    ) : DataItem(2)


    data class SeriesTagItem(
        val tag: Tag<Series>,
        val interactionListener: SeriesInteractionListener
    ) : DataItem(3)


}
//    data class HeaderDetailsItem(val items: Comics,) : DataItem()
//
//    data class TabItem(val items: CharacterDetailsFragment, ) : DataItem()


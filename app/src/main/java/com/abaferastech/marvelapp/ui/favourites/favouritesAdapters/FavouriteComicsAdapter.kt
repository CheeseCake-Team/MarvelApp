package com.abaferastech.marvelapp.ui.favourites.favouritesAdapters

import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.domain.models.Character
import com.abaferastech.marvelapp.domain.models.Comic
import com.abaferastech.marvelapp.ui.base.BaseAdapter
import com.abaferastech.marvelapp.ui.base.BaseInteractionListener
import com.abaferastech.marvelapp.ui.character.characters.CharactersInteractionListener
import com.abaferastech.marvelapp.ui.comic.comics.ComicsInteractionListener


class FavouriteComicsAdapter(listener: CharactersInteractionListener) :
    BaseAdapter<Comic>(object : ComicsInteractionListener {
        override fun onClickComic(comic: Comic) {}
    }) {

    override val layoutId = R.layout.item_comic_horizontal

}
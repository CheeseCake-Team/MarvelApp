package com.abaferastech.marvelapp.ui.comic.comics

import com.abaferastech.marvelapp.R
import com.abaferastech.marvelapp.data.remote.response.ComicDTO
import com.abaferastech.marvelapp.domain.models.Comic
import com.abaferastech.marvelapp.ui.base.BaseAdapter

class ComicsAdapter(listener: ComicsInteractionListener) :
    BaseAdapter<ComicDTO>(listener) {
    override val layoutId = R.layout.item_comic_horizontal
}




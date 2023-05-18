package com.abaferastech.marvelapp.ui.comic.comics

import com.abaferastech.marvelapp.data.domain.models.Comic
import com.abaferastech.marvelapp.ui.base.BaseInteractionListener

interface ComicsInteractionListener : BaseInteractionListener {
    fun onClickComic(comic: Comic)
}
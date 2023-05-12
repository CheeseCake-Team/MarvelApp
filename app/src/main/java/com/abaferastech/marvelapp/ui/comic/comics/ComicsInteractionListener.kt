package com.abaferastech.marvelapp.ui.comic.comics

import com.abaferastech.marvelapp.data.model.result.Characters
import com.abaferastech.marvelapp.data.model.result.Comics
import com.abaferastech.marvelapp.ui.base.BaseInteractionListener

interface ComicsInteractionListener : BaseInteractionListener {
    fun onClickComic(comic: Comics)
}